package org.likelion.kkokio.domain.menu.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.likelion.kkokio.domain.category.dto.another.CategoryDtoOnly;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "메뉴 정보 요청에 대한 응답 DTO")
public class MenuInfoResponseDTO extends BaseEntityDTO {
    @Schema(description = "메뉴 ID값")
    private Long menuId;

    @Schema(description = "메뉴 이름", example = "볶음밥")
    private String menuName;

    @Schema(description = "메뉴 가격", example = "6000")
    private Long menuPrice;

    @Schema(description = "메뉴 설명", example = "고기와 나물과 양념 그리고 밥")
    private String menuSummary;

    @Schema(description = "메뉴 대표 이미지 url", example = "https://storage.googleapis.com/kkokio_storage_image_bucket/*")
    private String menuImgUrl;

    private CategoryDtoOnly categoryDtoOnly;
}
