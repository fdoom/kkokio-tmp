package org.likelion.kkokio.global.base.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Schema(description = "생성, 수정, 삭제 시간 정보")
public class BaseEntityDTO {
    @Schema(description = "생성 시간 정보")
    private LocalDateTime createdAt;

    @Schema(description = "마지막 수정 시간 정보")
    private LocalDateTime updatedAt;

    @Schema(description = "삭제 시간 정보")
    private LocalDateTime deletedAt;
}
