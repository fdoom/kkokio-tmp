package org.likelion.kkokio.domain.adminAccount.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(title = "로그인 ID 변경 요청 DTO", description = "로그인 ID 변경 요청 DTO")
public record LoginIdUpdateDto(

        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영어 또는 숫자로 입력해주세요.")
        @NotBlank(message = "로그인 ID는 필수 입력값입니다.")
        @Size(min = 5 ,max = 50, message = "로그인 ID는 5자 이상 50자 이하로 입력해주세요.")
        @Schema(title = "로그인 ID", description = "로그인 ID", example = "test123",
                minLength = 5, maxLength = 50, nullable = false, required = true, requiredProperties = {"not blank", "영어 숫자만 가능"})
        String loginId
) {
}
