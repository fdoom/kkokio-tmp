package org.likelion.kkokio.domain.ordersMenu.repository;

import org.likelion.kkokio.domain.ordersMenu.entity.OrdersMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersMenuRepository extends JpaRepository<OrdersMenu, Long> {
    @Query("SELECT om FROM OrdersMenu om JOIN om.orders o WHERE o.deletedAt IS NULL AND o.orderId = :orderId")
    List<OrdersMenu> findAllByOrderId(Long orderId);
}
