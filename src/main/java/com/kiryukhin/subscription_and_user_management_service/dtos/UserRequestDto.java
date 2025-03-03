package com.kiryukhin.subscription_and_user_management_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequestDto {
    @Schema(description = "First name of user", example = "Aleksandr",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String firstName;
    @Schema(description = "Last name of user", example = "Pushkin",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String lastName;

    @NotNull
    @Email
    @Schema(description = "Email of user", example = "example@email.com",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
}
