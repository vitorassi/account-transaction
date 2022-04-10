package com.anderson.core.service.validation.imp;

import com.anderson.common.excpetion.BadRequestCustomException;
import com.anderson.core.model.Transaction;
import com.anderson.core.service.internal.ValidateTransaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class ValidateTransactionAmount implements ValidateTransaction {

    private static boolean amountValid(Transaction t) {
        return t.getAmount().compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public void validate(Transaction transaction) {
        Optional.ofNullable(transaction)
                .filter(ValidateTransactionAmount::amountValid)
                .orElseThrow(() -> new BadRequestCustomException("Invalid Amount"));

    }


}



