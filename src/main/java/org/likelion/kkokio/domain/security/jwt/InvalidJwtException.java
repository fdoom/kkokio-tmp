package org.likelion.kkokio.domain.security.jwt;

import lombok.Getter;
import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;


public class InvalidJwtException extends CustomException {
    @Getter
    private Throwable cause;
    public InvalidJwtException() {
        super(ErrorCode.INVALID_JWT);
    }

    public InvalidJwtException(Throwable cause) {
        super(ErrorCode.INVALID_JWT);
        this.cause = cause;
    }
}
