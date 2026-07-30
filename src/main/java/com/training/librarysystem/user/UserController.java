package com.training.librarysystem.user;

import com.training.librarysystem.auth.LoginDTO;
import com.training.librarysystem.auth.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    Should be in a controller
    @GetMapping()
    public String home() {
        return "Home page";
    }

    @GetMapping("/getAll")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public Users register(@RequestBody RegistrationDTO regData) {
        return userService.register(regData);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginData) {
        System.out.println(loginData);
        return userService.verify(loginData);
    }
}
