package org.likelion.kkokio.domain.ordersMenu.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.kkokio.domain.menu.dto.another.MenuDtoAndCategoryDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "주문 메뉴 및 카테고리 정보 DTO")
public class OrdersMenuDtoAndMenuDtoAndCategoryDto {
    private MenuDtoAndCategoryDto menuDtoAndCategoryDto;

    @Schema(description = "메뉴 수량")
    private int amount;
}
