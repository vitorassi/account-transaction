package com.anderson.core.port.input;

import com.anderson.core.model.Account;

import java.util.Optional;

public interface AccountService {

    Account save(Account account);

    Optional<Account> findByID(String accountId);

}
