package com.kiryukhin.subscription_and_user_management_service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExistsException extends ResponseStatusException {
    public UserAlreadyExistsException(String email) {
        super(HttpStatus.CONFLICT, "User with email " + email + " already exists!");
    }
}