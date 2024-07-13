package org.likelion.kkokio.domain.order.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "주문 정보 DTO")
public class OrderInfoResponseDTO extends BaseEntityDTO {
    @Schema(description = "주문 ID")
    private Long orderId;
    private List<OrdersMenuDtoAndMenuDtoAndCategoryDto> ordersMenuDtoAndMenuDtoAndCategoryDtoList;
}
