package com.training.librarysystem.user;

import com.training.librarysystem.auth.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

//    Write a code to validate all regData or do it with Validation dependency
    public Users register(RegistrationDTO regData) {
        Users user = new Users();
        user.setFirstName(regData.firstName());
        user.setLastName(regData.lastName());
        user.setAge(regData.age());

        user.setEmail(regData.email());

//        encode the password
        user.setPassword(encoder.encode(regData.password()));

        return repo.save(user);
    }

}
