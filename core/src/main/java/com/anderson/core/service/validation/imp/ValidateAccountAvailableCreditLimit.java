package com.anderson.core.service.validation.imp;

import com.anderson.common.excpetion.BadRequestCustomException;
import com.anderson.common.excpetion.ConflictCustomException;
import com.anderson.core.model.Account;
import com.anderson.core.model.Transaction;
import com.anderson.core.service.internal.ValidateAccount;
import com.anderson.core.service.out.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
public class ValidateAccountAvailableCreditLimit implements ValidateAccount {
    @Override
    public void validate(Account account) {

        Optional.ofNullable(account.getAvailableCreditLimit())
                .filter(bigDecimal -> amountValid(bigDecimal))
                .orElseThrow(() -> new BadRequestCustomException("Invalid value AvailableCreditLimit"));

    }


    private static boolean amountValid(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }
}



