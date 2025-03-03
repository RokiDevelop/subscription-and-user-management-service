package com.kiryukhin.subscription_and_user_management_service.repositories;

import com.kiryukhin.subscription_and_user_management_service.models.UserEntity;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(@Email String email);

}
