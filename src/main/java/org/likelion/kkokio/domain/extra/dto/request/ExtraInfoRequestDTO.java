package org.likelion.kkokio.domain.extra.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
@Schema(description = "메뉴 추가 옵션 요청에 대한 DTO")
public class ExtraInfoRequestDTO {

    @Schema(description = "메뉴 추가 옵션명", example = "계란 추가")
    @NotBlank(message = "빈칸이 아니여야 합니다.")
    private String extraName;

    @Schema(description = "메뉴 추가 옵션 가격", example = "300")
    @Positive(message = "메뉴 추가 옵션 가격은 1이상이여야 합니다.")
    private Long extraPrice;
}
