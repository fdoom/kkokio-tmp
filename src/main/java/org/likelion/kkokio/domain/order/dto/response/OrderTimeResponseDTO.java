package org.likelion.kkokio.domain.order.dto.response;

import lombok.Getter;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

import java.time.LocalDateTime;

@Getter
public class OrderTimeResponseDTO extends BaseEntityDTO {
    private Long orderId;
    private LocalDateTime cookingStartedAt;
    private LocalDateTime orderFinishedAt;
}
