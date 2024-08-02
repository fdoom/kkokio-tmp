package org.likelion.kkokio.domain.extra.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.likelion.kkokio.global.base.dto.BaseEntityDTO;

@Getter
public class ExtraInfoResponseDTO extends BaseEntityDTO {
    @Schema(description = "메뉴 추가 옵션 ID값")
    private Long extraId;

    @Schema(description = "메뉴 추가 옵션명", example = "계란 추가")
    private String extraName;

    @Schema(description = "메뉴 추가 옵션 가격", example = "300")
    private Long extraPrice;

}
