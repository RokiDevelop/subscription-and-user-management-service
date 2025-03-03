package com.kiryukhin.subscription_and_user_management_service.services;

import com.kiryukhin.subscription_and_user_management_service.dtos.UserDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.UserRequestDto;
import com.kiryukhin.subscription_and_user_management_service.models.UserEntity;

import java.util.UUID;

public interface UserService {
    UserDto getUserDtoById(UUID id);
    UserEntity getUserById(UUID id);

    UserDto createUserAndGetDto(UserRequestDto userRequestDto);

    UserDto updateUserByIdAndGetDto(UUID id, UserRequestDto userRequestDto);

    void deleteUserById(UUID id);
}
