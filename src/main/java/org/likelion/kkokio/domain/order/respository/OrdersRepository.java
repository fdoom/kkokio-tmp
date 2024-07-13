package org.likelion.kkokio.domain.order.respository;

import org.likelion.kkokio.domain.order.dto.response.OrderInfoResponseDTO;
import org.likelion.kkokio.domain.order.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
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

    @Query("SELECT o FROM Orders o JOIN o.store s JOIN s.category c " +
            "WHERE o.deletedAt IS NULL AND s.deletedAt IS NULL AND c.deletedAt IS NULL " +
            "AND s.storeId = :storeId AND c.categoryId = :categoryId")
    Page<Orders> findByStoreIdAndCategoryIdAndDeletedAtIsNull(Long storeId, Long categoryId, Pageable pageable);

    @Query("SELECT o FROM Orders o JOIN o.store s WHERE s.deletedAt IS NULL AND o.deletedAt IS NULL AND s.storeId =:storeId")
    Page<Orders> findByStoreIdAndDeletedAtIsNull(Long storeId, Pageable pageable);
}
