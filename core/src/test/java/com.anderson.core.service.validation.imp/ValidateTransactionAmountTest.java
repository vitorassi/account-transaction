package com.anderson.core.service.validation.imp;

import com.anderson.common.excpetion.BadRequestCustomException;
import com.anderson.core.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;


@ExtendWith(MockitoExtension.class)
class ValidateTransactionAmountTest {

    private ValidateTransactionAmount validateTransactionAmount;

    @BeforeEach
    public void init() {
        validateTransactionAmount = new ValidateTransactionAmount();
    }

    @Test
    @DisplayName("Test Transaction amount Success")
    public void testTransactionAmountSuccess() {
        try {

            Transaction transaction = Transaction.builder()
                    .amount(new BigDecimal("150.00"))
                    .build();

            validateTransactionAmount.validate(transaction);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Fail test");
        }
    }

    @Test
    @DisplayName("Test Transaction amount invalid")
    public void testTransactionAmountInvalid() {
        try {
            Transaction transaction = Transaction.builder()
                    .amount(new BigDecimal("-150.00"))
                    .build();

            validateTransactionAmount.validate(transaction);
            fail("Fail test");
        } catch (BadRequestCustomException e) {
            Assertions.assertEquals("Invalid Amount", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Fail test");
        }

    }
}