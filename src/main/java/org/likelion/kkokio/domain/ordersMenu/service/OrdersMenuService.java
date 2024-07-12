package org.likelion.kkokio.domain.ordersMenu.service;

import org.likelion.kkokio.domain.order.dto.request.OrderInfoRequestDTO;
import org.likelion.kkokio.domain.ordersMenu.dto.OrdersMenuDTO;

import java.util.List;

public interface OrdersMenuService {
    List<OrdersMenuDTO> createOrderInfo(Long orderId, List<OrderInfoRequestDTO> orderInfoRequestDTOList, Long storeId);
}
