package com.kiryukhin.subscription_and_user_management_service.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubscriptionAlreadyExistsException extends ResponseStatusException {
    public SubscriptionAlreadyExistsException(String serviceName, UUID userId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        super(HttpStatus.CONFLICT, String.format(
                "Subscription %s for user with ID %s already exists for the period from %s to %s",
                serviceName, userId, dateFrom, dateTo
        ));
    }
}
