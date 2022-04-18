package com.anderson.core.service.validation.imp;


import com.anderson.common.excpetion.BadRequestCustomException;
import com.anderson.common.excpetion.ConflictCustomException;
import com.anderson.core.model.Account;
import com.anderson.core.service.out.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateAvailableCreditLimitTest {

    private ValidateAccountAvailableCreditLimit validateAccountDocument;

    @BeforeEach
    public void init() {
        validateAccountDocument = new ValidateAccountAvailableCreditLimit();
    }


    @Test
    @DisplayName("Test valid available Credit Limit")
    public void testAvailableCreditLimitSuccess() {
        try {
            Account account = Account.builder().id(UUID.randomUUID())
                    .documentNumber("1234")
                    .availableCreditLimit(new BigDecimal("10.00"))
                    .build();

            validateAccountDocument.validate(account);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    @DisplayName("Test valid available Credit Limit")
    public void testAvailableCreditLimitFail() {
        try {
            Account account = Account.builder().id(UUID.randomUUID())
                    .documentNumber("1234")
                    .availableCreditLimit(new BigDecimal("-10.00"))
                    .build();

            validateAccountDocument.validate(account);
            fail("Fail test");

        }catch (BadRequestCustomException e){
            Assertions.assertEquals("Invalid value AvailableCreditLimit", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Fail test");

        }
    }

}