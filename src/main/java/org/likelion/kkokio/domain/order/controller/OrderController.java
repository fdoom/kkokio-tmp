package org.likelion.kkokio.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.order.dto.request.OrderInfoRequestDTO;
import org.likelion.kkokio.domain.order.dto.response.OrderInfoResponseDTO;
import org.likelion.kkokio.domain.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/info/{storeId}")
    public ResponseEntity<OrderInfoResponseDTO> createOrderInfo(@PathVariable("storeId") Long storeId, @RequestBody List<OrderInfoRequestDTO> orderInfoRequestDTOList) {
        return orderService.createOrderInfo(storeId, orderInfoRequestDTOList);
    }
}
