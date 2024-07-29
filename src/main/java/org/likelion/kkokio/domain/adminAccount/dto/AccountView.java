package org.likelion.kkokio.domain.adminAccount.dto;

import org.likelion.kkokio.domain.adminAccount.entity.AdminAccount;

public record AccountView(
        Long accountId,
        String accountLoginId,
        String accountName
) {
    public static AccountView from(AdminAccount adminAccount) {
        return new AccountView(
                adminAccount.getAccountId(),
                adminAccount.getAccountLoginId(),
                adminAccount.getAccountName()
        );
    }
}
