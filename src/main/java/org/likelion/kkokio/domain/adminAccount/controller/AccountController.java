package org.likelion.kkokio.domain.adminAccount.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.adminAccount.dto.*;
import org.likelion.kkokio.domain.adminAccount.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Account", description = "계정 관련 API")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원가입", description = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "요청 값 오류"),
            @ApiResponse(responseCode = "409", description = "중복된 로그인 ID"),
            @ApiResponse(responseCode = "403", description = "로그인 상태로 회원가입 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public AccountView register(
            @RequestBody @Valid AccountRegisterDto registerDto
    ) {
        return accountService.registerAccount(registerDto);
    }

    @PutMapping("/accounts/{accountId}/loginId")
    @Operation(summary = "로그인 ID 변경", description = "로그인 ID 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 ID 변경 성공"),
            @ApiResponse(responseCode = "400", description = "요청 값 오류"),
            @ApiResponse(responseCode = "404", description = "계정 없음"),
            @ApiResponse(responseCode = "409", description = "중복된 로그인 ID"),
            @ApiResponse(responseCode = "403", description = "로그인 상태로 로그인 ID 변경 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public AccountView updateLoginId(
            @PathVariable("accountId") Long accountId,
            @RequestBody @Valid LoginIdUpdateDto dto
    ) {
        String loginId = dto.loginId();
        return accountService.updateLoginId(accountId, loginId);
    }

    @PutMapping("/accounts/{accountId}/password")
    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공"),
            @ApiResponse(responseCode = "400", description = "요청 값 오류, 기존 비밀번호 불일치"),
            @ApiResponse(responseCode = "404", description = "계정 없음"),
            @ApiResponse(responseCode = "403", description = "로그인 상태로 비밀번호 변경 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")

    })
    public AccountView updatePassword(
            @PathVariable("accountId") Long accountId,
            @RequestBody @Valid LoginPasswordUpdateDto dto
    ) {
        String oldPassword = dto.currentPassword();
        String newPassword = dto.newPassword();
        return accountService.updatePassword(accountId, oldPassword, newPassword);
    }

    @PutMapping("/accounts/{accountId}/name")
    @Operation(summary = "이름 변경", description = "이름 변경")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이름 변경 성공"),
            @ApiResponse(responseCode = "400", description = "요청 값 오류"),
            @ApiResponse(responseCode = "404", description = "계정 없음"),
            @ApiResponse(responseCode = "403", description = "로그인 상태로 이름 변경 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    public AccountView updateName(
            @PathVariable("accountId") Long accountId,
            @RequestBody @Valid NameUpdateDto dto
    ) {
        String name = dto.name();
        return accountService.updateName(accountId, name);
    }
}
