package org.likelion.kkokio.domain.adminAccount.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AccountRegisterDto {
    @NotBlank(message = "로그인 ID는 필수 입력값입니다.")
    @Size(min = 5 ,max = 50, message = "로그인 ID는 5자 이상 50자 이하로 입력해주세요.")
    private String loginId;
    @NotBlank(message = "로그인 비밀번호는 필수 입력값입니다.")
    @Size(min = 8, max = 50, message = "로그인 비밀번호는 8자 이상 50자 이하로 입력해주세요.")
    private String loginPassword;
    @NotBlank(message = "사용자 이름은 필수 입력값입니다.")
    @Size(min = 1, max = 50, message = "사용자 이름은 1자 이상 50자 이하로 입력해주세요.")
    private String name;
}
