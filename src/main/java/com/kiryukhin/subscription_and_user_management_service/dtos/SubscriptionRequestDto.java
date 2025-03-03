package com.kiryukhin.subscription_and_user_management_service.dtos;

import com.kiryukhin.subscription_and_user_management_service.models.enums.DigitalServicesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriptionRequestDto {
    @Schema(description = "Subscription service", example = "VK_MUSIC", allowableValues = {"YOUTUBE", "PREMIUM", "VK_MUSIC", "YANDEX_PLUS", "NETFLIX"},
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private DigitalServicesEnum subscriptionService;

    @Schema(description = "Subscription start date and time", example = "2025-03-03T10:15:30",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime subscriptionStartDatetime;

    @Schema(description = "Subscription expiration date and time", example = "2025-04-03T10:15:30",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime subscriptionEndDatetime;
}
