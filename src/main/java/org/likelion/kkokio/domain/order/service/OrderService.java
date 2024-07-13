package org.likelion.kkokio.domain.order.service;

import org.likelion.kkokio.domain.order.dto.request.OrderInfoRequestDTO;
import org.likelion.kkokio.domain.order.dto.response.OrderTimeResponseDTO;
import org.likelion.kkokio.domain.order.dto.response.OrderInfoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<OrderInfoResponseDTO> createOrderInfo(Long storeId, List<OrderInfoRequestDTO> orderInfoRequestDTOList);

    ResponseEntity<OrderInfoResponseDTO> updateOrderInfo(Long orderId, List<OrderInfoRequestDTO> orderInfoRequestDTOList);

    ResponseEntity<OrderTimeResponseDTO> cookOrderInfo(Long orderId);

    ResponseEntity<Void> deleteOrderInfo(Long orderId);

    ResponseEntity<OrderTimeResponseDTO> finishOrderInfo(Long orderId);

    ResponseEntity<Page<OrderInfoResponseDTO>> getOrderInfoByStoreIdAndCategoryId(Long storeId, Long categoryId, Pageable pageable);

    ResponseEntity<Page<OrderInfoResponseDTO>> getOrderInfoByStoreId(Long storeId, Pageable pageable);

    ResponseEntity<OrderInfoResponseDTO> getOrderInfoByOrderId(Long orderId);
}
