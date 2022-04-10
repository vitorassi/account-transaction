package com.anderson.core.service.imput;

import com.anderson.core.model.Account;
import com.anderson.core.port.input.AccountService;
import com.anderson.core.service.internal.Validation;
import com.anderson.core.service.out.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
public class AccountServiceImp implements AccountService {

    private AccountRepository accountRepository;
    private Validation validationAccountService;

    @Override
    public Account save(Account account) {

        log.info("Save account");

        validationAccountService.validate(account);
        Account accountSave = accountRepository.save(account);

        return accountSave;
    }

    @Override
    public Optional<Account> findByID(String uuid) {
        //// TODO: 08/04/2022


        Optional<Account> optionalAccount = accountRepository.findBy(UUID.fromString(uuid));

        return optionalAccount;
    }
}
