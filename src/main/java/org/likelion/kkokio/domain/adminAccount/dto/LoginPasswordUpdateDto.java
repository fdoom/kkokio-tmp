package org.likelion.kkokio.domain.adminAccount.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(title = "로그인 비밀번호 변경 요청 DTO", description = "로그인 비밀번호 변경 요청 DTO")
public record LoginPasswordUpdateDto(

        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 영어 또는 숫자로 입력해주세요.")
        @NotBlank(message = "현재 로그인 비밀번호는 필수 입력값입니다.")
        @Size(min = 8, max = 50, message = "로그인 비밀번호는 8자 이상 50자 이하로 입력해주세요.")
        @Schema(title = "현재 로그인 비밀번호", description = "현재 로그인 비밀번호", example = "test123asdfassfda4",
                minLength = 8, maxLength = 50, nullable = false, required = true, requiredProperties = {"not blank", "not samePassword", "영어 숫자만 가능"})
        String currentPassword,


        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 영어 또는 숫자로 입력해주세요.")
        @NotBlank(message = "새로운 로그인 비밀번호는 필수 입력값입니다.")
        @Size(min = 8, max = 50, message = "로그인 비밀번호는 8자 이상 50자 이하로 입력해주세요.")
        @Schema(title = "새로운 로그인 비밀번호", description = "새로운 로그인 비밀번호", example = "test123asdfassfda4",
                minLength = 8, maxLength = 50, nullable = false, required = true, requiredProperties = {"not blank", "not samePassword", "영어 숫자만 가능"})
        String newPassword
) {

        @AssertFalse(message = "현재 비밀번호와 새로운 비밀번호는 같을 수 없습니다.")
        public boolean isSamePassword() {
                if (currentPassword == null || newPassword == null) {
                        return false;
                }
                return currentPassword.equals(newPassword);
        }


}
