package com.training.librarysystem.auth;

import com.training.librarysystem.auth.dto.LoginDTO;
import com.training.librarysystem.auth.dto.RegistrationDTO;
import com.training.librarysystem.user.UserService;
import com.training.librarysystem.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register(@RequestBody RegistrationDTO regData) {
        return userService.register(regData);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginData) {
        return userService.verify(loginData);
    }
}
