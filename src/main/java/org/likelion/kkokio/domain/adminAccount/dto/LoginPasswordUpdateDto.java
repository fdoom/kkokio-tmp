package org.likelion.kkokio.domain.adminAccount.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginPasswordUpdateDto(
        @NotBlank(message = "현재 로그인 비밀번호는 필수 입력값입니다.")
        @Size(min = 8, max = 50, message = "로그인 비밀번호는 8자 이상 50자 이하로 입력해주세요.")
        String currentPassword,
        @NotBlank(message = "새로운 로그인 비밀번호는 필수 입력값입니다.")
        @Size(min = 8, max = 50, message = "로그인 비밀번호는 8자 이상 50자 이하로 입력해주세요.")
        String newPassword
) {
}
