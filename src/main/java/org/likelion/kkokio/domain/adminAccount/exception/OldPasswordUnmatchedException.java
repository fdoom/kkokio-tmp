package org.likelion.kkokio.domain.adminAccount.exception;

import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;

public class OldPasswordUnmatchedException extends CustomException {
    public OldPasswordUnmatchedException() {
        super(ErrorCode.OLD_PASSWORD_UNMATCHED);
    }
}
