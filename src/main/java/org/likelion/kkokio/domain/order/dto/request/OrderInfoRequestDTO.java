package org.likelion.kkokio.domain.order.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class OrderInfoRequestDTO {
    @Positive
    private Long menuId;
    @Positive
    private int amount;
}
