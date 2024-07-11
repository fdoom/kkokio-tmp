package org.likelion.kkokio.global.base.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // ACCOUNT
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다."),

    // STORE
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "식당 정보가 존재하지 않습니다."),

    STORE_USER_NOTMATH(HttpStatus.FORBIDDEN, "식당 가게 주인만 해당 정보를 수정 및 삭제하는 것이 가능합니다."),

    STORE_ALREADY_DELETED(HttpStatus.NOT_FOUND, "식당 정보는 이미 삭제되었습니다.")

    ;

    private final HttpStatus status;
    private final String message;
}