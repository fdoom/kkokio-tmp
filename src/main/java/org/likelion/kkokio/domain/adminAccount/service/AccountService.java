package org.likelion.kkokio.domain.adminAccount.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.kkokio.domain.adminAccount.dto.LoginIdUpdateDto;
import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.adminAccount.exception.AccountNotFoundException;
import org.likelion.kkokio.domain.adminAccount.repository.AdminAccountRepository;
import org.likelion.kkokio.domain.adminAccount.dto.AccountRegisterDto;
import org.likelion.kkokio.domain.adminAccount.dto.AccountView;
import org.likelion.kkokio.domain.adminAccount.exception.AccountIntegrityViolation;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AdminAccountRepository adminAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("!authenticated")
    public AccountView registerAccount(AccountRegisterDto accountRegisterDto) {
        String encodedPassword = passwordEncoder.encode(accountRegisterDto.getLoginPassword());
        // 회원가입 로직
        AdminAccount created = AdminAccount.createNewBuilder()
                .accountLoginId(accountRegisterDto.getLoginId())
                .accountLoginPassword(encodedPassword)
                .accountName(accountRegisterDto.getName())
                .build();

        try {
            AdminAccount saved = adminAccountRepository.save(created);
            return AccountView.from(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new AccountIntegrityViolation();
        }
    }

    public AccountView updateLoginId(Long accountId, String loginId) {
        AdminAccount account = adminAccountRepository.findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
        account.setAccountLoginId(loginId);

        try {
            AdminAccount saved = adminAccountRepository.save(account);
            return AccountView.from(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new AccountIntegrityViolation();
        }

    }
}
