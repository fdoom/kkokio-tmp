package org.likelion.kkokio.domain.security.jwt;

import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;

public class TokenExpiredException extends CustomException {
    public TokenExpiredException() {
        super(ErrorCode.EXPIRED_JWT);
    }
}
