package com.kiryukhin.subscription_and_user_management_service.repositories;

import com.kiryukhin.subscription_and_user_management_service.models.SubscriptionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, UUID> {

    Optional<SubscriptionEntity> findByIdAndUserId(UUID subId, UUID userId );

    @Query("SELECT s FROM SubscriptionEntity s " +
            "WHERE s.user.id = :userId " +
            "AND (s.subscriptionStartDatetime < :endDatetime AND s.subscriptionEndDatetime > :startDatetime)")
    List<SubscriptionEntity> findOverlappingSubscriptions(
            @Param("userId") UUID userId,
            @Param("startDatetime") LocalDateTime startDatetime,
            @Param("endDatetime") LocalDateTime endDatetime
    );

    List<SubscriptionEntity> findAllByUserIdAndSubscriptionEndDatetimeAfter(UUID userId, LocalDateTime localDateTimeNow);

    @Query("SELECT s.subscriptionService, COUNT(s) as count " +
            "FROM SubscriptionEntity s " +
            "GROUP BY s.subscriptionService " +
            "ORDER BY count DESC")
    List<Object[]> findMostPopularSubscriptions(Pageable pageable);
}
