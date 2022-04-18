package com.anderson.core.service.validation.imp;

import com.anderson.common.excpetion.ConflictCustomException;
import com.anderson.core.model.Account;
import com.anderson.core.model.Operation;
import com.anderson.core.model.Transaction;
import com.anderson.core.service.internal.ValidateTransaction;
import com.anderson.core.service.out.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class ValidateTransactionLimitCredit implements ValidateTransaction {

    private AccountRepository repository;

    @Override
    public void validate(Transaction transaction) {

        if(transaction.getOperation().equals(Operation.PAGAMENTO)) {
            return;
        }

        Account account = repository.findBy(transaction.getAccountId()).orElseThrow(() -> {
            throw new ConflictCustomException("Conflict \"Account\" don't exist in database");
        });

        if (transaction.getAmount().doubleValue() > account.getAvailableCreditLimit().doubleValue()){
            throw new ConflictCustomException("Conflict \"Account\" don't exist Limit Credit");
        }

    }


}



