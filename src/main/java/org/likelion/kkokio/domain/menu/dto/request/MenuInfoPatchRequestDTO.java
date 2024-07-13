package org.likelion.kkokio.domain.menu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "메뉴 정보 변경 요청 DTO")
public class MenuInfoPatchRequestDTO extends MenuInfoRequestDTO {
    @Schema(description = "카테고리 ID값")
    private Long categoryId;
}
