package com.kiryukhin.subscription_and_user_management_service.services;

import com.kiryukhin.subscription_and_user_management_service.dtos.PopularSubscriptionDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.SubscriptionDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.SubscriptionRequestDto;

import java.util.List;
import java.util.UUID;

public interface SubscriptionService {
    void deleteSubscriptionByUserIdAndSubscriptionId(UUID userId, UUID subId);

    List<SubscriptionDto> getActiveSubscriptionsByUserIdAndGetDto(UUID userId);

    SubscriptionDto addSubscriptionAndGetDto(UUID userId, SubscriptionRequestDto subscriptionRequestDto);

    List<PopularSubscriptionDto> getMostPopularSubscriptionDtos();
}
