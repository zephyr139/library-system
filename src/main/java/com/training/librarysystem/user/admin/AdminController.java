package com.training.librarysystem.user.admin;

import com.training.librarysystem.auth.GrantRoleDTO;
import com.training.librarysystem.user.UserService;
import com.training.librarysystem.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/delete/{id}")
    public Users deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/grant-role/{id}")
    public Users grantRole(@PathVariable int id, @RequestBody GrantRoleDTO updateReq) {
        return userService.grantRole(id, updateReq.role().toString());
    }
}
