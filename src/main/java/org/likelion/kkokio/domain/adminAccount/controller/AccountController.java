package org.likelion.kkokio.domain.adminAccount.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.adminAccount.dto.*;
import org.likelion.kkokio.domain.adminAccount.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountView register(
            @RequestBody @Valid AccountRegisterDto registerDto
    ) {
        return accountService.registerAccount(registerDto);
    }

    @PutMapping("/accounts/{accountId}/loginId")
    public AccountView updateLoginId(
            @PathVariable("accountId") Long accountId,
            @RequestBody @Valid LoginIdUpdateDto dto
    ) {
        String loginId = dto.loginId();
        return accountService.updateLoginId(accountId, loginId);
    }

    @PutMapping("/accounts/{accountId}/password")
    public AccountView updatePassword(
            @PathVariable("accountId") Long accountId,
            @RequestBody @Valid LoginPasswordUpdateDto dto
    ) {
        String oldPassword = dto.currentPassword();
        String newPassword = dto.newPassword();
        return accountService.updatePassword(accountId, oldPassword, newPassword);
    }

    @PutMapping("/accounts/{accountId}/name")
    public AccountView updatePassword(
            @PathVariable("accountId") Long accountId,
            @RequestBody @Valid NameUpdateDto dto
    ) {
        String name = dto.name();
        return accountService.updateName(accountId, name);
    }
}
