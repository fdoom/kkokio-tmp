package org.likelion.kkokio.domain.order.respository;

import org.likelion.kkokio.domain.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    Optional<Orders> findByOrderIdAndDeletedAtIsNull(Long orderId);
}
