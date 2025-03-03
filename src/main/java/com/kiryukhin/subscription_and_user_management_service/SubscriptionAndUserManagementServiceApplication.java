package com.kiryukhin.subscription_and_user_management_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "User API",
				version = "1.0",
				description = "API documentation for managing users and subscriptions"
		)
)
@SpringBootApplication
public class SubscriptionAndUserManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionAndUserManagementServiceApplication.class, args);
	}

}
