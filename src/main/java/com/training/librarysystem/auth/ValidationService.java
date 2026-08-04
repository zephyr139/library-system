package com.training.librarysystem.auth;

import com.training.librarysystem.user.UserRepo;
import com.training.librarysystem.user.Users;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private UserRepo userRepo;

    public boolean isOriginalEmail(@Email String email) {
        Users user = userRepo.findByEmail(email);

        return user == null;
    }

    public boolean isPasswordMatch(String password, String password2) {
        return password.equals(password2);
    }
//    Class that will handel existing Email not matching passwords and so on
//    It uses custom exceptions to give understandable feedback




}
