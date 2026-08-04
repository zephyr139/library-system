package com.training.librarysystem.exceptions;

public class CustomRoleNotFoundException extends IllegalArgumentException {
    public CustomRoleNotFoundException(String message) {
        super(message);
    }
}
