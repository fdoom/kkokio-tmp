package org.likelion.kkokio.domain.adminAccount.controller;

import lombok.RequiredArgsConstructor;
import org.likelion.kkokio.domain.adminAccount.dto.AccountRegisterDto;
import org.likelion.kkokio.domain.adminAccount.dto.AccountView;
import org.likelion.kkokio.domain.adminAccount.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountView registerAccount(
            AccountRegisterDto accountRegisterDto
    ) {
        return accountService.registerAccount(accountRegisterDto);
    }




}
