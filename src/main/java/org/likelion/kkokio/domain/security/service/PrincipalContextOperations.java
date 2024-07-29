package org.likelion.kkokio.domain.security.service;

import org.likelion.kkokio.domain.security.dto.UserDto;

import java.util.Optional;

public interface PrincipalContextOperations {
    /**
     * @return 현재 인증된 사용자 정보를 반환한다. 인증되지 않았다면 빈 Optional::empty 를 반환한다.
     */
    public Optional<UserDto> getCurrentUser();

    /**
     * 현재 인증된 사용자의 ID를 반환한다.
     * @return 현재 인증된 사용자의 ID를 반환한다. 인증되지 않았다면 빈 Optional::empty 를 반환한다.
     */
    Optional<Long> getCurrentUserId();

}
