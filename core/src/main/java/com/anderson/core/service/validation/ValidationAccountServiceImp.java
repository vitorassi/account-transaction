package com.anderson.core.service.validation;

import com.anderson.core.model.Account;
import com.anderson.core.service.internal.AbstractionValidation;
import com.anderson.core.service.out.AccountRepository;
import com.anderson.core.service.validation.imp.ValidateAccountAvailableCreditLimit;
import com.anderson.core.service.validation.imp.ValidateAccountDocument;

import java.util.List;

public class ValidationAccountServiceImp extends AbstractionValidation<Account> {

    public ValidationAccountServiceImp(AccountRepository accountRepository) {
        this.validations = List.of(
                new ValidateAccountDocument(accountRepository),
                new ValidateAccountAvailableCreditLimit()
        );
    }

}
