package org.likelion.kkokio.domain.ordersMenu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.kkokio.domain.menu.dto.another.MenuDtoUsingOrdersMenu;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersMenuDTO {
    private MenuDtoUsingOrdersMenu menuDTOUsingOrdersMenu;
    private int amount;
}
