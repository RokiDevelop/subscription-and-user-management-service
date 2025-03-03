package com.kiryukhin.subscription_and_user_management_service.dtos;

import com.kiryukhin.subscription_and_user_management_service.models.enums.DigitalServicesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PopularSubscriptionDto {
    @Schema(description = "Subscription service Enum value", example = "YANDEX_PLUS")
    private DigitalServicesEnum subscriptionService;

    @Schema(description = "Subscription service display name", example = "Яндекс Плюс")
    private String subscriptionServiceDisplayName;

    @Schema(description = "Number of top services", example = "3")
    private long count;
}
