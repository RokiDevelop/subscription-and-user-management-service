package com.kiryukhin.subscription_and_user_management_service.services;

import com.kiryukhin.subscription_and_user_management_service.dtos.UserDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.UserRequestDto;
import com.kiryukhin.subscription_and_user_management_service.exeptions.UserAlreadyExistsException;
import com.kiryukhin.subscription_and_user_management_service.exeptions.UserNotFoundException;
import com.kiryukhin.subscription_and_user_management_service.mappers.UserEntityMapper;
import com.kiryukhin.subscription_and_user_management_service.models.UserEntity;
import com.kiryukhin.subscription_and_user_management_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public UserDto getUserDtoById(UUID id) {
        UserEntity user = getUserEntity(id);
        return userEntityMapper.toUserDto(user);
    }

    @Override
    public UserEntity getUserById(UUID id) {
        return getUserEntity(id);
    }

    @Override
    @Transactional
    public UserDto createUserAndGetDto(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            log.error("User with email {} already exists.", userRequestDto.getEmail());
            throw new UserAlreadyExistsException(userRequestDto.getEmail());
        }

        UserEntity user = userEntityMapper.toUserEntity(userRequestDto);
        userRepository.save(user);
        log.info("User with email {} created successfully.", userRequestDto.getEmail());
        return userEntityMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUserByIdAndGetDto(UUID id, UserRequestDto userRequestDto) {
        UserEntity user = getUserEntity(id);

        if (userRequestDto.getEmail() != null && !userRequestDto.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(userRequestDto.getEmail())) {
                log.error("Email {} already exists.", userRequestDto.getEmail());
                throw new UserAlreadyExistsException(userRequestDto.getEmail());
            }
        }

        userEntityMapper.updatePartial(user, userRequestDto);
        userRepository.save(user);
        log.info("User with id {} updated successfully.", id);
        return userEntityMapper.toUserDto(user);
    }

    @Override
    @Transactional
    public void deleteUserById(UUID id) {
        UserEntity user = getUserEntity(id);
        userRepository.delete(user);
        log.info("User with id {} deleted successfully.", id);
    }

    private UserEntity getUserEntity(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found!"));
    }
}
