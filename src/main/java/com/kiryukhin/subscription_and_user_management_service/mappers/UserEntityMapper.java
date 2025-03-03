package com.kiryukhin.subscription_and_user_management_service.mappers;

import com.kiryukhin.subscription_and_user_management_service.dtos.UserDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.UserRequestDto;
import com.kiryukhin.subscription_and_user_management_service.models.UserEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserDto toUserDto(UserEntity userEntity);
    UserEntity toUserEntity(UserRequestDto userRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePartial(@MappingTarget UserEntity entity, UserRequestDto dto);
}
