package com.kiryukhin.subscription_and_user_management_service.mappers;

import com.kiryukhin.subscription_and_user_management_service.dtos.SubscriptionDto;
import com.kiryukhin.subscription_and_user_management_service.dtos.SubscriptionRequestDto;
import com.kiryukhin.subscription_and_user_management_service.models.SubscriptionEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionEntityMapper {
    @Mapping(target = "userId", source = "user.id")
    SubscriptionDto toSubscriptionDto(SubscriptionEntity subscriptionEntity);

    @Mapping(target = "userId", source = "user.id")
    List<SubscriptionDto> toSubscriptionDtoList(List<SubscriptionEntity> subscriptionEntities);

    SubscriptionEntity toSubscriptionEntity(SubscriptionRequestDto subscriptionRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePartial(@MappingTarget SubscriptionEntity entity, SubscriptionRequestDto dto);
}
