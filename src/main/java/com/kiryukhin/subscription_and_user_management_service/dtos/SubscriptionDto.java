package com.kiryukhin.subscription_and_user_management_service.dtos;

import com.kiryukhin.subscription_and_user_management_service.models.enums.DigitalServicesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SubscriptionDto {

    @Schema(description = "Subscription ID", example = "f4c72f72-9334-4a73-89c4-d0f5d828a0b2")
    @NotNull
    private UUID id;

    @Schema(description = "Subscription service", example = "YouTube")
    @NotNull
    private DigitalServicesEnum subscriptionService;

    @Schema(description = "Subscription start date and time", example = "2025-03-03T10:15:30")
    @NotNull
    private LocalDateTime subscriptionStartDatetime;

    @Schema(description = "Subscription expiration date and time", example = "2025-04-03T10:15:30")
    @NotNull
    private LocalDateTime subscriptionEndDatetime;

    @Schema(description = "User ID that is subscribed", example = "a57e12f1-cc50-4a1c-bffb-9935c3a6825a")
    @NotNull
    private UUID userId;
}
