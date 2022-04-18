package com.anderson.mapper;

import com.anderson.adapter.database.entity.AccountEntity;
import com.anderson.core.model.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AccountMapper {

    public static Account toAccount(AccountEntity entity) {
        return Account.builder()
                .id(entity.getId())
                .documentNumber(entity.getDocumentNumber())
                .availableCreditLimit(entity.getAvailableCreditLimit())
                .build();
    }

    public static AccountEntity toAccountEntity(Account account) {
        return AccountEntity.builder()
                .id(account.getId())
                .documentNumber(account.getDocumentNumber())
                .availableCreditLimit(account.getAvailableCreditLimit())
                .build();
    }

}
