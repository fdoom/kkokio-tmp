package org.likelion.kkokio.domain.category.dto.another;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

@Getter
@Schema(description = "카테고리 정보")
public class CategoryDtoOnly extends BaseEntityDTO {
    @Schema(description = "카테고리 ID값", example = "1")
    private Long categoryId;

    @Schema(description = "카테고리 이름", example = "한식")
    private String categoryName;
}
