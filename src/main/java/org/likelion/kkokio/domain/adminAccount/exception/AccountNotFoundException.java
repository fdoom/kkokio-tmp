package org.likelion.kkokio.domain.adminAccount.exception;

import org.likelion.kkokio.global.base.exception.CustomException;
import org.likelion.kkokio.global.base.exception.ErrorCode;

public class AccountNotFoundException extends CustomException {
    public AccountNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
