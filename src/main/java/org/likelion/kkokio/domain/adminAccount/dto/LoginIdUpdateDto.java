package org.likelion.kkokio.domain.adminAccount.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginIdUpdateDto(
        @NotBlank(message = "로그인 ID는 필수 입력값입니다.")
        @Size(min = 5 ,max = 50, message = "로그인 ID는 5자 이상 50자 이하로 입력해주세요.")
        String loginId
) {
}
