package com.anderson.core.service.validation;

import com.anderson.core.model.Transaction;
import com.anderson.core.service.internal.AbstractionValidation;
import com.anderson.core.service.out.AccountRepository;
import com.anderson.core.service.validation.imp.ValidateTransactionAccount;
import com.anderson.core.service.validation.imp.ValidateTransactionAmount;
import com.anderson.core.service.validation.imp.ValidateTransactionLimitCredit;
import com.anderson.core.service.validation.imp.ValidateTransactionOperation;

import java.util.List;


public class ValidationTransactionServiceImp extends AbstractionValidation<Transaction> {

    public ValidationTransactionServiceImp(AccountRepository accountRepository) {
        this.validations = List.of(
                new ValidateTransactionAccount(accountRepository),
                new ValidateTransactionOperation(),
                new ValidateTransactionAmount(),
                new ValidateTransactionLimitCredit(accountRepository)
        );
    }


}
