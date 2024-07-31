package org.likelion.kkokio.domain.adminAccount.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(title = "사용자 이름 변경 요청 DTO", description = "사용자 이름 변경 요청 DTO")
public record NameUpdateDto(

        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "이름은 영어 또는 숫자로 입력해주세요.")
        @NotBlank(message = "사용자 이름은 필수 입력값입니다.")
        @Size(min = 1, max = 50, message = "사용자 이름은 1자 이상 50자 이하로 입력해주세요.")
        @Schema(title = "사용자 이름", description = "사용자 이름", example = "홍길동",
                minLength = 1, maxLength = 50, nullable = false, required = true, requiredProperties = {"not blank", "영어 숫자만 가능"})
        String name
) {
}
