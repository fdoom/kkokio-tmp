package org.likelion.kkokio.domain.order.respository;

import org.likelion.kkokio.domain.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByOrderIdAndDeletedAtIsNull(Long orderId);

    @Query("SELECT o FROM Orders o JOIN o.store s JOIN s.adminAccount a " +
            "WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND o.deletedAt IS NULL AND o.cookingStartedAt IS NULL AND o.orderFinishedAt IS NULL " +
            "AND o.orderId = :orderId AND a.accountId = :accountId")
    Optional<Orders> findByOrderIdAndAdminAccountIdAndDeletedAtIsNullAndCookingStartedAtIsNull(Long orderId, Long accountId);

    @Query("SELECT o FROM Orders o JOIN o.store s JOIN s.adminAccount a " +
            "WHERE a.deletedAt IS NULL AND s.deletedAt IS NULL AND o.createdAt IS NOT NULL AND o.deletedAt IS NULL AND o.cookingStartedAt IS NOT NULL AND o.orderFinishedAt IS NULL " +
            "AND o.orderId = :orderId AND a.accountId = :accountId")
    Optional<Orders> findByOderIdAndAdminAccountIdAndDeletedAtIsNullAndOrderFinishedAtIsNull(Long orderId, Long accountId);
}
