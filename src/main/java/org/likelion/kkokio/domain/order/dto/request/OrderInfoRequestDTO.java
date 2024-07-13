package org.likelion.kkokio.domain.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
@Schema(description = "주문 요청 정보 DTO")
public class OrderInfoRequestDTO {
    @Schema(description = "메뉴 ID값")
    private Long menuId;

    @Schema(description = "메뉴 수량")
    @Positive(message = "메뉴 수량은 1이상입니다.")
    private int amount;
}
