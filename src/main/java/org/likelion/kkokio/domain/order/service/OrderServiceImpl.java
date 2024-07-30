package org.likelion.kkokio.domain.order.service;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.order.dto.request.OrderInfoRequestDTO;
import org.likelion.kkokio.domain.order.dto.response.OrderTimeResponseDTO;
import org.likelion.kkokio.domain.order.dto.response.OrderInfoResponseDTO;
import org.likelion.kkokio.domain.order.entity.Orders;
import org.likelion.kkokio.domain.order.respository.OrdersRepository;
import org.likelion.kkokio.domain.ordersMenu.dto.OrdersMenuDtoAndMenuDtoAndCategoryDto;
import org.likelion.kkokio.domain.ordersMenu.service.OrdersMenuService;
import org.likelion.kkokio.domain.security.service.SecurityService;
import org.likelion.kkokio.domain.store.entity.Store;
import org.likelion.kkokio.domain.store.repository.StoreRepository;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{
    private final OrdersRepository ordersRepository;
    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;
    private final OrdersMenuService ordersMenuService;
    private final SecurityService securityService;

    @Override
    public ResponseEntity<OrderInfoResponseDTO> createOrderInfo(Long storeId, List<OrderInfoRequestDTO> orderInfoRequestDTOList) {
        Store store = storeRepository.findByStoreIdAndDeletedAtIsNull(storeId)
                .orElseThrow(()-> new CustomException(ErrorCode.STORE_NOT_FOUND));

        Orders orders = Orders.builder()
                .store(store)
                .build();
        ordersRepository.save(orders);

        List<OrdersMenuDtoAndMenuDtoAndCategoryDto> ordersMenuDtoAndMenuDtoAndCategoryDtoList = ordersMenuService.createOrderInfo(orders.getOrderId(), orderInfoRequestDTOList, storeId);
        OrderInfoResponseDTO orderInfoResponseDTO = OrderInfoResponseDTO.builder()
                .ordersMenuDtoAndMenuDtoAndCategoryDtoList(ordersMenuDtoAndMenuDtoAndCategoryDtoList)
                .build();
        modelMapper.map(orders, orderInfoResponseDTO);
        return ResponseEntity.ok(orderInfoResponseDTO);
    }

    @Override
    public ResponseEntity<OrderInfoResponseDTO> updateOrderInfo(Long orderId, List<OrderInfoRequestDTO> orderInfoRequestDTOList) {
        Orders orders = ordersRepository.findByOrderIdAndAdminAccountIdAndDeletedAtIsNullAndCookingStartedAtIsNull(orderId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(()-> new CustomException(ErrorCode.ORDER_NOT_FOUND_OR_NOT_UPDATED));

        List<OrdersMenuDtoAndMenuDtoAndCategoryDto> ordersMenuDtoAndMenuDtoAndCategoryDtoList = ordersMenuService.updateOrderInfo(orderId, orderInfoRequestDTOList);
        orders.updatedInfo();
        ordersRepository.save(orders);

        OrderInfoResponseDTO orderInfoResponseDTO = OrderInfoResponseDTO.builder()
                .ordersMenuDtoAndMenuDtoAndCategoryDtoList(ordersMenuDtoAndMenuDtoAndCategoryDtoList)
                .build();
        modelMapper.map(orders, orderInfoResponseDTO);
        return ResponseEntity.ok(orderInfoResponseDTO);
    }

    @Override
    public ResponseEntity<OrderTimeResponseDTO> cookOrderInfo(Long orderId) {
        Orders orders = ordersRepository.findByOrderIdAndAdminAccountIdAndDeletedAtIsNullAndCookingStartedAtIsNull(orderId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(()-> new CustomException(ErrorCode.ORDER_NOT_FOUND_OR_NOT_UPDATED));

        orders.updateCookOrderInfo();
        ordersRepository.save(orders);
        return ResponseEntity.ok(modelMapper.map(orders, OrderTimeResponseDTO.class));
    }

    @Override
    public ResponseEntity<Void> deleteOrderInfo(Long orderId) {
        Orders orders = ordersRepository.findByOrderIdAndAdminAccountIdAndDeletedAtIsNullAndCookingStartedAtIsNull(orderId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(()-> new CustomException(ErrorCode.ORDER_NOT_FOUND_OR_NOT_UPDATED));
        orders.deletedInfo();
        ordersRepository.save(orders);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<OrderTimeResponseDTO> finishOrderInfo(Long orderId) {
        Orders orders = ordersRepository.findByOderIdAndAdminAccountIdAndDeletedAtIsNullAndOrderFinishedAtIsNull(orderId, securityService.getCurrentUserId().orElseThrow())
                .orElseThrow(()-> new CustomException(ErrorCode.ORDER_NOT_FOUND_OR_NOT_UPDATED));
        orders.updateFinishedOrderInfo();
        ordersRepository.save(orders);
        return ResponseEntity.ok(modelMapper.map(orders, OrderTimeResponseDTO.class));
    }

    @Override
    public ResponseEntity<Page<OrderInfoResponseDTO>> getOrderInfoByStoreIdAndCategoryId(Long storeId, Long categoryId, Pageable pageable) {
        return ResponseEntity.ok(
                ordersRepository.findByStoreIdAndCategoryIdAndDeletedAtIsNull(storeId, categoryId, pageable)
                        .map( orders -> {
                            List<OrdersMenuDtoAndMenuDtoAndCategoryDto> ordersMenuDtoAndMenuDtoAndCategoryDtoList =
                                    ordersMenuService.getOrdersMenuInfoList(orders.getOrderId());
                            OrderInfoResponseDTO orderInfoResponseDTO = OrderInfoResponseDTO.builder()
                                    .ordersMenuDtoAndMenuDtoAndCategoryDtoList(ordersMenuDtoAndMenuDtoAndCategoryDtoList)
                                    .build();
                            modelMapper.map(orders, orderInfoResponseDTO);
                            return orderInfoResponseDTO;
                        }));
    }

    @Override
    public ResponseEntity<Page<OrderInfoResponseDTO>> getOrderInfoByStoreId(Long storeId, Pageable pageable) {
        return ResponseEntity.ok(
                ordersRepository.findByStoreIdAndDeletedAtIsNull(storeId, pageable)
                        .map(orders -> {
                            List<OrdersMenuDtoAndMenuDtoAndCategoryDto> ordersMenuDtoAndMenuDtoAndCategoryDtoList =
                                    ordersMenuService.getOrdersMenuInfoList(orders.getOrderId());
                            OrderInfoResponseDTO orderInfoResponseDTO = OrderInfoResponseDTO.builder()
                                    .ordersMenuDtoAndMenuDtoAndCategoryDtoList(ordersMenuDtoAndMenuDtoAndCategoryDtoList)
                                    .build();
                            modelMapper.map(orders, orderInfoResponseDTO);
                            return orderInfoResponseDTO;
                        }));
    }

    @Override
    public ResponseEntity<OrderInfoResponseDTO> getOrderInfoByOrderId(Long orderId) {
        Orders orders = ordersRepository.findByOrderIdAndDeletedAtIsNull(orderId)
                .orElseThrow(()-> new CustomException(ErrorCode.ORDER_NOT_FOUND));
        List<OrdersMenuDtoAndMenuDtoAndCategoryDto> ordersMenuDtoAndMenuDtoAndCategoryDtoList =
                ordersMenuService.getOrdersMenuInfoList(orders.getOrderId());
        OrderInfoResponseDTO orderInfoResponseDTO = OrderInfoResponseDTO.builder()
                .ordersMenuDtoAndMenuDtoAndCategoryDtoList(ordersMenuDtoAndMenuDtoAndCategoryDtoList)
                .build();
        modelMapper.map(orders, orderInfoResponseDTO);
        return ResponseEntity.ok(orderInfoResponseDTO);
    }
}
