package org.likelion.kkokio.global.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // ACCOUNT
    USER_NOT_FOUND(org.springframework.http.HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}