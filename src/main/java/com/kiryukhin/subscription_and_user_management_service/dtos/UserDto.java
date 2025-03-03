package com.kiryukhin.subscription_and_user_management_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    @Schema(description = "User ID", example = "f4c72f72-9334-4a73-89c4-d0f5d828a0b2",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID id;
    @Schema(description = "First name of user", example = "Aleksandr")
    private String firstName;
    @Schema(description = "Last name of user", example = "Pushkin")
    private String lastName;

    @NotNull
    @Email
    @Schema(description = "Email of user", example = "example@email.com")
    private String email;
}
