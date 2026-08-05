package com.training.librarysystem.user;

import com.training.librarysystem.auth.JwtService;
import com.training.librarysystem.auth.dto.LoginDTO;
import com.training.librarysystem.auth.dto.RegistrationDTO;
import com.training.librarysystem.auth.ValidationService;
import com.training.librarysystem.exceptions.CustomRoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ValidationService validationService;

//    Write a code to validate all regData or do it with Validation dependency
    public Users register(RegistrationDTO regData) {
        Users user = new Users();

        user.setFirstName(regData.firstName());
        user.setLastName(regData.lastName());
//        Default role is MEMBER
        user.setRole(Role.MEMBER);
        user.setAge(regData.age());

        if (!validationService.isOriginalEmail(regData.email())) throw new RuntimeException("Email is already in use");
        user.setEmail(regData.email());
//
        if (!validationService.isPasswordMatch(regData.password(), regData.confirmPassword())) throw new RuntimeException("Passwords do not match");
//        encode the password
        user.setPassword(encoder.encode(regData.password()));

        user.setEnabled(true);
        user.setCreatedAt(new Date());

        return repo.save(user);
    }

//    login and providing JWT token for user
//    TODO: research
    public String verify(LoginDTO loginData) {
        try {
            Authentication auth =
                    authManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.email(), loginData.password()));

            if (auth.isAuthenticated()) {
                return jwtService.generateToken(loginData.email());
            }
        } catch (AuthenticationException e) {
            return "Failure";
//            TODO: deal with this
        }

        return "Failure";
    }

    public List<Users> getAllUsers() {
        return repo.findAll();
    }

    public Users deleteUser(long id) {
        Users user = repo.findById(id).get();
        repo.deleteById(id);
        return user;
    }

    public Users grantRole(long userId, String roleName){
        Role role;
//      TODO:  Resolve
        try {
            role = Role.valueOf(roleName);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CustomRoleNotFoundException("No such role is provided by the system!");
        }

        Users user = repo.findById(userId).get();
        user.setRole(role);

        return repo.save(user);
    }
}
