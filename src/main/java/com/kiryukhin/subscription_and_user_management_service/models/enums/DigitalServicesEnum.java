package com.kiryukhin.subscription_and_user_management_service.models.enums;

import lombok.Getter;

@Getter
public enum DigitalServicesEnum {
    YOUTUBE ("YouTube"),
    PREMIUM ("Premium"),
    VK_MUSIC ("VK Музыка"),
    YANDEX_PLUS ("Яндекс Плюс"),
    NETFLIX ("Netflix");

    private final String displayName;

    DigitalServicesEnum(String displayName) {
        this.displayName = displayName;
    }
}
