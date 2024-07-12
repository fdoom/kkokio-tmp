package org.likelion.kkokio.domain.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.kkokio.domain.ordersMenu.dto.OrdersMenuDtoAndMenuDtoAndCategoryDto;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfoResponseDTO extends BaseEntityDTO {
    private Long orderId;
    private List<OrdersMenuDtoAndMenuDtoAndCategoryDto> ordersMenuDtoAndMenuDtoAndCategoryDtoList;
}
