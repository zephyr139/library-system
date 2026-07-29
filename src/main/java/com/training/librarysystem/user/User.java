package com.training.librarysystem.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private int age;

    private boolean enabled;

    private Date createdAt;
}
