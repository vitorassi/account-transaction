package com.anderson.core.service.out;

import com.anderson.core.model.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    Account save(Account account);

    Optional<Account> findBy(UUID uuid);

    Optional<Account> findByDocument(String documentNumber);

}
