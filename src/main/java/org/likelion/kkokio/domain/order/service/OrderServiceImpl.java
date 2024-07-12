package org.likelion.kkokio.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.order.dto.request.OrderInfoRequestDTO;
import org.likelion.kkokio.domain.order.dto.response.OrderInfoResponseDTO;
import org.likelion.kkokio.domain.order.entity.Orders;
import org.likelion.kkokio.domain.order.respository.OrdersRepository;
import org.likelion.kkokio.domain.ordersMenu.dto.OrdersMenuDTO;
import org.likelion.kkokio.domain.ordersMenu.service.OrdersMenuService;
import org.likelion.kkokio.domain.store.entity.Store;
import org.likelion.kkokio.domain.store.repository.StoreRepository;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrdersRepository ordersRepository;
    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;
    private final OrdersMenuService ordersMenuService;

    @Override
    public ResponseEntity<OrderInfoResponseDTO> createOrderInfo(Long storeId, List<OrderInfoRequestDTO> orderInfoRequestDTOList) {
        Store store = storeRepository.findByStoreIdAndDeletedAtIsNull(storeId)
                .orElseThrow(()-> new CustomException(ErrorCode.STORE_NOT_FOUND));

        Orders orders = Orders.builder()
                .store(store)
                .build();
        ordersRepository.save(orders);

        List<OrdersMenuDTO> ordersMenuDTOList = ordersMenuService.createOrderInfo(orders.getOrderId(), orderInfoRequestDTOList, storeId);
        OrderInfoResponseDTO orderInfoResponseDTO = OrderInfoResponseDTO.builder()
                .ordersMenuDTOList(ordersMenuDTOList)
                .build();
        modelMapper.map(orders, orderInfoResponseDTO);
        return ResponseEntity.ok(orderInfoResponseDTO);
    }
}
