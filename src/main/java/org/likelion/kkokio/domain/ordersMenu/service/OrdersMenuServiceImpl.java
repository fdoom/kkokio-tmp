package org.likelion.kkokio.domain.ordersMenu.service;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.category.dto.another.CategoryDtoUsingOrdersMenu;
import org.likelion.kkokio.domain.menu.dto.another.MenuDtoUsingOrdersMenu;
import org.likelion.kkokio.domain.menu.entity.Menu;
import org.likelion.kkokio.domain.menu.repository.MenuRepository;
import org.likelion.kkokio.domain.order.dto.request.OrderInfoRequestDTO;
import org.likelion.kkokio.domain.order.entity.Orders;
import org.likelion.kkokio.domain.order.respository.OrdersRepository;
import org.likelion.kkokio.domain.ordersMenu.dto.OrdersMenuDTO;
import org.likelion.kkokio.domain.ordersMenu.entity.OrdersMenu;
import org.likelion.kkokio.domain.ordersMenu.entity.id.OrderMenuId;
import org.likelion.kkokio.domain.ordersMenu.repository.OrdersMenuRepository;
import org.likelion.kkokio.domain.store.repository.StoreRepository;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersMenuServiceImpl implements OrdersMenuService {
    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;
    private final OrdersRepository ordersRepository;
    private final OrdersMenuRepository ordersMenuRepository;
    private final StoreRepository storeRepository;

    @Override
    public List<OrdersMenuDTO> createOrderInfo(Long orderId, List<OrderInfoRequestDTO> orderInfoRequestDTOList, Long storeId) {
        Orders orders = modelMapper.map(ordersRepository.findByOrderIdAndDeletedAtIsNull(orderId)
                .orElseThrow(() -> new CustomException(ErrorCode.ORDER_NOT_FOUND)), Orders.class);

        ordersMenuRepository.saveAll(
                orderInfoRequestDTOList.stream().map(menuInfo -> {
                    Menu menu = modelMapper.map(menuRepository.findByMenuIdAndStoreIdAndDeletedAtIsNull(menuInfo.getMenuId(), storeId)
                                    .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND)),
                            Menu.class);

                    OrderMenuId orderMenuId = OrderMenuId.builder()
                            .menu_id(menuInfo.getMenuId())
                            .order_id(orderId)
                            .build();

                    return OrdersMenu.builder()
                            .id(orderMenuId)
                            .orders(orders)
                            .menu(menu)
                            .amount(menuInfo.getAmount())
                            .build();
                }).toList()
        );

        return ordersMenuRepository.findAllByOrderId(orderId).stream().map(ordersMenu -> {
            MenuDtoUsingOrdersMenu menuDTOUsingOrdersMenu = MenuDtoUsingOrdersMenu.builder()
                    .categoryDTOusingOrdersMenu(modelMapper.map(ordersMenu.getMenu().getCategory(), CategoryDtoUsingOrdersMenu.class))
                    .build();
            modelMapper.map(ordersMenu.getMenu(), menuDTOUsingOrdersMenu);

            return OrdersMenuDTO.builder()
                    .menuDTOUsingOrdersMenu(menuDTOUsingOrdersMenu)
                    .amount(ordersMenu.getAmount())
                    .build();
        }).toList();
    }
}
