package org.likelion.kkokio.domain.order.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

import java.time.LocalDateTime;

@Getter
@Schema(description = "주문 요리 시작 및 완료 시작 처리 정보 DTO")
public class OrderTimeResponseDTO extends BaseEntityDTO {
    @Schema(description = "주문 ID값")
    private Long orderId;

    @Schema(description = "주문 시작 시간")
    private LocalDateTime cookingStartedAt;

    @Schema(description = "주문 완료 시간")
    private LocalDateTime orderFinishedAt;
}
