package org.likelion.kkokio.domain.menu.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
@Schema(description = "메뉴 정보 요청에 대한 DTO")
public class MenuInfoRequestDTO {
    @Schema(description = "메뉴 이름", example = "볶음밥")
    @NotBlank(message = "메뉴 이름은 빈칸이 아니여야 합니다.")
    private String menuName;

    @Schema(description = "메뉴 가격", example = "6000")
    @Positive(message = "메뉴 가격은 1이상입니다.")
    private Long menuPrice;

    @Schema(description = "메뉴 설명", example = "고기와 나물과 양념 그리고 밥")
    @NotBlank(message = "메뉴 설명은 빈칸이 아니여야 합니다.")
    private String menuSummary;
}
