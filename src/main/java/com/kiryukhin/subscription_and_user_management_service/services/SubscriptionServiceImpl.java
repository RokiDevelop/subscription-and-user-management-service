package com.kiryukhin.subscription_and_user_management_service.services;

import com.kiryukhin.subscription_and_user_management_service.dtos.PopularSubscriptionDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.SubscriptionDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.SubscriptionRequestDto;
import com.kiryukhin.subscription_and_user_management_service.exeptions.SubscriptionAlreadyExistsException;
import com.kiryukhin.subscription_and_user_management_service.exeptions.SubscriptionNotFoundException;
import com.kiryukhin.subscription_and_user_management_service.mappers.SubscriptionEntityMapper;
import com.kiryukhin.subscription_and_user_management_service.models.SubscriptionEntity;
import com.kiryukhin.subscription_and_user_management_service.models.UserEntity;
import com.kiryukhin.subscription_and_user_management_service.models.enums.DigitalServicesEnum;
import com.kiryukhin.subscription_and_user_management_service.repositories.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionEntityMapper subscriptionEntityMapper;
    private final UserService userService;

    @Override
    @Transactional
    public void deleteSubscriptionByUserIdAndSubscriptionId(UUID userId, UUID subId) {
        log.info("Attempting to delete subscription with id={} for userId={}", subId, userId);
        try {
            SubscriptionEntity subscription = subscriptionRepository.findByIdAndUserId(subId, userId)
                    .orElseThrow(() -> new SubscriptionNotFoundException(
                            "Subscription with id %s and user_id %s not found".formatted(subId, userId)));
            subscriptionRepository.delete(subscription);
            log.info("Successfully deleted subscription with id={} for userId={}", subId, userId);
        } catch (SubscriptionNotFoundException e) {
            log.warn("Failed to delete subscription: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<SubscriptionDto> getActiveSubscriptionsByUserIdAndGetDto(UUID userId) {
        log.info("Fetching active subscriptions for userId={}", userId);
        LocalDateTime localDateTimeNow = LocalDateTime.now();
        List<SubscriptionEntity> subscriptions = subscriptionRepository
                .findAllByUserIdAndSubscriptionEndDatetimeAfter(userId, localDateTimeNow);
        log.info("Found {} active subscriptions for userId={}", subscriptions.size(), userId);
        return subscriptionEntityMapper.toSubscriptionDtoList(subscriptions);
    }

    @Override
    @Transactional
    public SubscriptionDto addSubscriptionAndGetDto(UUID userId, SubscriptionRequestDto subscriptionRequestDto) {
        log.info("Adding subscription for userId={} with service={}", userId, subscriptionRequestDto.getSubscriptionService());
        UserEntity user = userService.getUserById(userId);

        if (subscriptionRequestDto.getSubscriptionStartDatetime() == null) {
            subscriptionRequestDto.setSubscriptionStartDatetime(LocalDateTime.now());
        }

        if (subscriptionRequestDto.getSubscriptionEndDatetime() == null) {
            subscriptionRequestDto.setSubscriptionEndDatetime(subscriptionRequestDto.getSubscriptionStartDatetime().plusMonths(1));
        }

        List<SubscriptionEntity> overlappingSubscriptions = subscriptionRepository
                .findOverlappingSubscriptions(userId,
                        subscriptionRequestDto.getSubscriptionStartDatetime(),
                        subscriptionRequestDto.getSubscriptionEndDatetime());

        for (SubscriptionEntity subscription : overlappingSubscriptions) {
            if (subscription.getSubscriptionService().equals(subscriptionRequestDto.getSubscriptionService())) {
                log.warn("Subscription already exists for userId={} with service={}", userId, subscriptionRequestDto.getSubscriptionService());
                throw new SubscriptionAlreadyExistsException(
                        subscription.getSubscriptionService().getDisplayName(),
                        userId,
                        subscription.getSubscriptionStartDatetime(),
                        subscription.getSubscriptionEndDatetime()
                );
            }
        }

        SubscriptionEntity subscription = subscriptionEntityMapper.toSubscriptionEntity(subscriptionRequestDto);
        subscription.setUser(user);
        subscriptionRepository.save(subscription);
        log.info("Successfully added subscription with id={} for userId={}", subscription.getId(), userId);
        return subscriptionEntityMapper.toSubscriptionDto(subscription);
    }

    @Override
    public List<PopularSubscriptionDto> getMostPopularSubscriptionDtos() {
        int numberOfTop = 3;
        log.info("Fetching top {} most popular subscriptions", numberOfTop);

        List<Object[]> mostPopularSubscriptionObjects = subscriptionRepository.findMostPopularSubscriptions(PageRequest.of(0, numberOfTop));

        List<PopularSubscriptionDto> result = mostPopularSubscriptionObjects.stream()
                .map(obj -> new PopularSubscriptionDto((DigitalServicesEnum) obj[0],
                        ((DigitalServicesEnum) obj[0]).getDisplayName(),
                        ((Number) obj[1]).longValue()))
                .collect(Collectors.toList());

        log.info("Retrieved {} popular subscriptions", result.size());
        return result;
    }
}
