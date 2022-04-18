package com.anderson.application.mapper;

import com.anderson.application.adapter.http.dto.AccountCreateDTO;
import com.anderson.application.adapter.http.dto.AccountDTO;
import com.anderson.core.model.Account;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountMapper {

    public static AccountDTO toAccountDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId().toString())
                .documentNumber(account.getDocumentNumber())
                .availableCreditLimit(account.getAvailableCreditLimit())
                .build();
    }

    public static Account toAccount(AccountCreateDTO account) {
        return Account.builder()
                .documentNumber(account.getDocumentNumber())
                .availableCreditLimit(account.getAvailableCreditLimit())
                .build();
    }
}
