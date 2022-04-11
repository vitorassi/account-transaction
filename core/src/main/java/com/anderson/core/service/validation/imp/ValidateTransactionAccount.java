package com.anderson.core.service.validation.imp;

import com.anderson.common.excpetion.ConflictCustomException;
import com.anderson.core.model.Transaction;
import com.anderson.core.service.internal.ValidateTransaction;
import com.anderson.core.service.out.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class ValidateTransactionAccount implements ValidateTransaction {

    private AccountRepository repository;

    @Override
    public void validate(Transaction transaction) {

        validAccountInformed(transaction);

        validIfExist(transaction);

    }

    protected void validAccountInformed(Transaction transaction) {

        log.info("Valid Account informed");

        Optional.ofNullable(transaction)
                .orElseThrow(() -> new ConflictCustomException("Conflict \"Account\" don't informed"));
    }

    protected void validIfExist(Transaction transaction) {

        log.info("Valid Account exist");

        repository.findBy(transaction.getAccountId()).orElseThrow(() -> {
            throw new ConflictCustomException("Conflict \"Account\" don't exist in database");
        });

    }

}



