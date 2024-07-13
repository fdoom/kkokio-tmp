package org.likelion.kkokio.domain.category.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Schema(description = "카테고리 정보를 담고 있는 DTO")
public class CategoryInfoRequestDTO {

    @NotBlank(message = "카테고리 이름은 빈칸이 아니여야 합니다.")
    @Size(max = 150, message = "카테고리 이름은 150글자 이하여야 합니다.")
    @Schema(description = "카테고리 이름", example = "한식")
    private String categoryName;
}
