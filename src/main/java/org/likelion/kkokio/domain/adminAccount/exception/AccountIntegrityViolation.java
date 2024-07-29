package org.likelion.kkokio.domain.adminAccount.exception;

import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;

public class AccountIntegrityViolation extends CustomException {
    public AccountIntegrityViolation() {
        super(ErrorCode.DUPLICATED_USER);
    }
}
