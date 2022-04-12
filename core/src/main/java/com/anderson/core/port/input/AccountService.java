package com.anderson.core.port.input;

import com.anderson.core.model.Account;

import java.rmi.server.UID;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

    Account save(Account account);

    Optional<Account> findByID(UUID accountId);

}
