package com.kiryukhin.subscription_and_user_management_service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SubscriptionNotFoundException extends ResponseStatusException {
    public SubscriptionNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}