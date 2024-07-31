package org.likelion.kkokio.domain.adminAccount.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(title = "회원가입 요청 DTO", description = "회원가입 요청 DTO")
public class AccountRegisterDto {

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영어 또는 숫자로 입력해주세요.")
    @NotBlank(message = "로그인 ID는 필수 입력값입니다.")
    @Size(min = 5 ,max = 50, message = "로그인 ID는 5자 이상 50자 이하로 입력해주세요.")
    @Schema(title = "로그인 ID", description = "로그인 ID", example = "test123",
            minLength = 5, maxLength = 50, nullable = false, required = true, requiredProperties = {"not blank", "영어 숫자만 가능"})
    private String loginId;

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "비밀번호는 영어 또는 숫자로 입력해주세요.")
    @NotBlank(message = "로그인 비밀번호는 필수 입력값입니다.")
    @Size(min = 8, max = 50, message = "로그인 비밀번호는 8자 이상 50자 이하로 입력해주세요.")
    @Schema(title = "로그인 비밀번호", description = "로그인 비밀번호", example = "test123asdfassfda4",
            minLength = 8, maxLength = 50, nullable = false, required = true, requiredProperties = {"not blank", "영어 숫자만 가능"})
    private String loginPassword;

    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "이름은 영어 또는 숫자로 입력해주세요.")
    @NotBlank(message = "사용자 이름은 필수 입력값입니다.")
    @Size(min = 1, max = 50, message = "사용자 이름은 1자 이상 50자 이하로 입력해주세요.")
    @Schema(title = "사용자 이름", description = "사용자 이름", example = "홍길동",
            minLength = 1, maxLength = 50, nullable = false, required = true, requiredProperties = {"not blank", "영어 숫자만 가능"})
    private String name;
}
