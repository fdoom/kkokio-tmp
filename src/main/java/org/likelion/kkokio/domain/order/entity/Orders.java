package org.likelion.kkokio.domain.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.kkokio.domain.store.entity.Store;
import org.likelion.kkokio.global.base.entity.BaseEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "createdAt", column = @Column(name = "order_create_at", nullable = false, updatable = false)),
        @AttributeOverride(name = "updatedAt", column = @Column(name = "order_update_at", nullable = false)),
        @AttributeOverride(name = "deletedAt", column = @Column(name = "order_delete_at"))
})
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    private LocalDateTime cookingStartedAt;
    private LocalDateTime orderFinishedAt;

    public void updatedInfo() {
        super.updatedInfo();
    }

    public void updateCookOrderInfo() {
        this.cookingStartedAt = LocalDateTime.now();
    }

    public void updateFinishedOrderInfo() {
        this.orderFinishedAt = LocalDateTime.now();
    }
}
