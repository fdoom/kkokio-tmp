package org.likelion.kkokio.domain.adminAccount.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NameUpdateDto(
        @NotBlank(message = "사용자 이름은 필수 입력값입니다.")
        @Size(min = 1, max = 50, message = "사용자 이름은 1자 이상 50자 이하로 입력해주세요.")
        String name
) {
}
