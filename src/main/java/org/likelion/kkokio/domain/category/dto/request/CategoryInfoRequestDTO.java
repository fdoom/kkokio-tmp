package org.likelion.kkokio.domain.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CategoryInfoRequestDTO {
    @NotBlank(message = "카테고리 이름은 빈칸이 아니여야 합니다.")
    @Size(max = 150, message = "카테고리 이름은 150글자 이하여야 합니다.")
    private String categoryName;
}
