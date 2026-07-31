package com.training.librarysystem.user;

import com.training.librarysystem.auth.JwtService;
import com.training.librarysystem.auth.LoginDTO;
import com.training.librarysystem.auth.RegistrationDTO;
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

//    Write a code to validate all regData or do it with Validation dependency
    public Users register(RegistrationDTO regData) {
        Users user = new Users();

        user.setFirstName(regData.firstName());
        user.setLastName(regData.lastName());
//        Default role is member
        user.setRole(Role.MEMBER);
        user.setAge(regData.age());
        user.setEmail(regData.email());
//
//        TODO: fix the equality of the passwords
//        if (!(regData.password() == regData.confirmPassword())) {
//            System.out.println(regData.password());
//            System.out.println(regData.confirmPassword());
//            throw new BadCredentialsException("Passwords do not match");
//        }
//
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
}
