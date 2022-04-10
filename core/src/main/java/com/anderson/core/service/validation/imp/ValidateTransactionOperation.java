package com.anderson.core.service.validation.imp;

import com.anderson.common.excpetion.BadRequestCustomException;
import com.anderson.core.model.Transaction;
import com.anderson.core.service.internal.ValidateTransaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class ValidateTransactionOperation implements ValidateTransaction {

    @Override
    public void validate(Transaction transaction) {

        log.info("Validate Operation");
        Optional.ofNullable(transaction)
                .filter(
                        t -> !Objects.isNull(t.getOperation())
                ).orElseThrow(() -> new BadRequestCustomException("Operation don't informed"));

    }

}



