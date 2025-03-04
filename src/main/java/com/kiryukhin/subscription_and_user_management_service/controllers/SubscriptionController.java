package com.kiryukhin.subscription_and_user_management_service.controllers;

import com.kiryukhin.subscription_and_user_management_service.dtos.PopularSubscriptionDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.SubscriptionDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.SubscriptionRequestDto;
import com.kiryukhin.subscription_and_user_management_service.services.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Subscriptions", description = "Subscription management API")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/users/{id}/subscriptions")
    @Operation(summary = "Add subscription", responses = {
            @ApiResponse(responseCode = "200", description = "Subscription added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<SubscriptionDto> addSubscription(@PathVariable("id") UUID userId,
                                                           @Valid @RequestBody SubscriptionRequestDto subscriptionRequestDto) {
        SubscriptionDto result = subscriptionService.addSubscriptionAndGetDto(userId, subscriptionRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/users/{id}/subscriptions")
    @Operation(summary = "Get All Active subscriptions by user id", responses = {
            @ApiResponse(responseCode = "200", description = "Fetched active subscriptions")
    })
    public ResponseEntity<List<SubscriptionDto>> getSubscriptionsByUserId(@PathVariable("id") UUID userId) {
        List<SubscriptionDto> result = subscriptionService.getActiveSubscriptionsByUserIdAndGetDto(userId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/users/{id}/subscriptions/{subId}")
    @Operation(summary = "Delete subscription", responses = {
            @ApiResponse(responseCode = "204", description = "Subscription deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Subscription not found")
    })
    public ResponseEntity<Void> deleteSubscription(@PathVariable("id") UUID userId,
                                                   @PathVariable("subId") UUID subId) {
        subscriptionService.deleteSubscriptionByUserIdAndSubscriptionId(userId, subId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subscriptions/top")
    @Operation(summary = "Get top subscriptions", responses = {
            @ApiResponse(responseCode = "200", description = "Fetched top subscriptions")
    })
    public ResponseEntity<List<PopularSubscriptionDto>> getTopSubscriptions() {
        List<PopularSubscriptionDto> result = subscriptionService.getMostPopularSubscriptionDtos();
        return ResponseEntity.ok(result);
    }
}
