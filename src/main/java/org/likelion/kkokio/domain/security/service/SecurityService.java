package org.likelion.kkokio.domain.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.likelion.kkokio.domain.adminAccount.repository.AdminAccountRepository;
import org.likelion.kkokio.domain.security.dto.UserDto;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Component
@Slf4j
public class SecurityService {



    public Optional<UserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 인증이 안된 경우
        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        // 인증이 된 경우
        if (authentication instanceof UsernamePasswordAuthenticationToken token &&
                token.getPrincipal() instanceof UserDto userDto) {
            return Optional.of(userDto);
        }
        log.error("Unexpected authentication type: {}", authentication.getClass());
        return Optional.empty();
    }

    public Optional<Long> getCurrentUserId() {
        return getCurrentUser().map(UserDto::getUserId);
    }


}
