package org.likelion.kkokio.domain.category.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.likelion.kkokio.domain.store.dto.response.StoreInfoResponseDTO;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "카테고리 요청에 대한 응답 정보를 담고 있는 DTO")
public class CategoryInfoResponseDTO extends BaseEntityDTO {
    @Schema(description = "카테고리 ID값", example = "1")
    private Long categoryId;

    @Schema(description = "카테고리 이름", example = "한식")
    private String categoryName;

    private StoreInfoResponseDTO storeInfoResponseDTO;
}
