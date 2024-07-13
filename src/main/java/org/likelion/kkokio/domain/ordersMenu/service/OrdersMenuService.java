package org.likelion.kkokio.domain.ordersMenu.service;

import org.likelion.kkokio.domain.order.dto.request.OrderInfoRequestDTO;
import org.likelion.kkokio.domain.order.entity.Orders;
import org.likelion.kkokio.domain.ordersMenu.dto.OrdersMenuDtoAndMenuDtoAndCategoryDto;

import java.util.List;

public interface OrdersMenuService {
    List<OrdersMenuDtoAndMenuDtoAndCategoryDto> createOrderInfo(Long orderId, List<OrderInfoRequestDTO> orderInfoRequestDTOList, Long storeId);

    List<OrdersMenuDtoAndMenuDtoAndCategoryDto> updateOrderInfo(Long orderId, List<OrderInfoRequestDTO> orderInfoRequestDTOList);
}
