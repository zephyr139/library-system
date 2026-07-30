package com.training.librarysystem.user;

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

//    Write a code to validate all regData or do it with Validation dependency
    public Users register(RegistrationDTO regData) {
        Users user = new Users();

//        Default role is member
        user.setRole(Role.MEMBER);

        user.setFirstName(regData.firstName());
        user.setLastName(regData.lastName());
        user.setAge(regData.age());

        if (!emailFormatValidation(regData.email())) {
            throw new RuntimeException("Invalid email format");
        }
        user.setEmail(regData.email());

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
                return "Success";
            }
        } catch (AuthenticationException e) {
            return "Failure";
        }

        return "Failure";
    }

//    Checks for email format
    private boolean emailFormatValidation(String email) {
        return email.contains("@");
    }

    public List<Users> getAllUsers() {
        return repo.findAll();
    }
}
