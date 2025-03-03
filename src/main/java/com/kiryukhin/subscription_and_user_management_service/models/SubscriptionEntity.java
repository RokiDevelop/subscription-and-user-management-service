package com.kiryukhin.subscription_and_user_management_service.models;

import com.kiryukhin.subscription_and_user_management_service.models.enums.DigitalServicesEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "subscriptions",
        indexes = {
                @Index(name = "idx_digital_service", columnList = "sub_service_name")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString(callSuper = true)
public class SubscriptionEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_service_name", nullable = false)
    private DigitalServicesEnum subscriptionService;

    @Column(name = "subscription_start_datetime", nullable = false)
    private LocalDateTime subscriptionStartDatetime;

    @Column(name = "subscription_end_datetime", nullable = false)
    private LocalDateTime subscriptionEndDatetime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SubscriptionEntity that = (SubscriptionEntity) object;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
