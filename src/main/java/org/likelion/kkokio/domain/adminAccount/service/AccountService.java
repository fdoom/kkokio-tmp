package org.likelion.kkokio.domain.adminAccount.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;
import org.likelion.kkokio.domain.adminAccount.exception.AccountNotFoundException;
import org.likelion.kkokio.domain.adminAccount.exception.OldPasswordUnmatchedException;
import org.likelion.kkokio.domain.adminAccount.repository.AdminAccountRepository;
import org.likelion.kkokio.domain.adminAccount.dto.AccountRegisterDto;
import org.likelion.kkokio.domain.adminAccount.dto.AccountView;
import org.likelion.kkokio.domain.adminAccount.exception.AccountIntegrityViolation;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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

    @PreAuthorize("isAuthenticated() and @securityService.currentUserId.get() == #accountId")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AccountView updateLoginId(Long accountId, String loginId) {
        AdminAccount account = adminAccountRepository.findByIdWithLock(accountId)
                .orElseThrow(AccountNotFoundException::new);
        account.setAccountLoginId(loginId);

        try {
            AdminAccount saved = adminAccountRepository.save(account);
            return AccountView.from(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new AccountIntegrityViolation();
        }

    }

    @PreAuthorize("isAuthenticated() and @securityService.currentUserId.get() == #accountId")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AccountView updatePassword(Long accountId, String oldPassword, String newPassword) {
        AdminAccount account = adminAccountRepository.findByIdWithLock(accountId)
                .orElseThrow(AccountNotFoundException::new);
        // 기존 비밀번호 체크
        String encodedOldPassword = passwordEncoder.encode(oldPassword);
        if (!account.getAccountLoginPassword().equals(encodedOldPassword)) {
            throw new OldPasswordUnmatchedException();
        }
        // 비밀번호 업데이트
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        account.setAccountLoginPassword(encodedNewPassword);
        try {
            AdminAccount saved = adminAccountRepository.save(account);
            return AccountView.from(saved);
        } catch (DataIntegrityViolationException ex) {
            log.error("Error occur while update password");
            throw new CustomException(ErrorCode.ACCOUNT_DB_ERROR);
        }
    }

    @PreAuthorize("isAuthenticated() and @securityService.currentUserId.get() == #accountId")
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public AccountView updateName(Long accountId, String name) {
        AdminAccount account = adminAccountRepository.findByIdWithLock(accountId)
                .orElseThrow(AccountNotFoundException::new);
        account.setAccountName(name);
        try {
            AdminAccount saved = adminAccountRepository.save(account);
            return AccountView.from(saved);
        } catch (DataIntegrityViolationException ex) {
            log.error("Error occur while update name");
            throw new CustomException(ErrorCode.ACCOUNT_DB_ERROR);
        }
    }
}
