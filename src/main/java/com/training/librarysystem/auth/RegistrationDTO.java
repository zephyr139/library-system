package com.training.librarysystem.auth;

public record RegistrationDTO(
        String firstName,
        String lastName,
        int age,
        String email,
        String password,
        String confirmPassword) {
}
