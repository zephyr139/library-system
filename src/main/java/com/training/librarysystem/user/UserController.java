package com.training.librarysystem.user;

import com.training.librarysystem.auth.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    Should be in a controller
    @GetMapping()
    public String home() {
        return "Home page";
    }

    @PostMapping("/register")
    public Users register(@RequestBody RegistrationDTO regData) {
        return userService.register(regData);
    }
}
