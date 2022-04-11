package com.anderson.core.service.validation.imp;

import com.anderson.common.excpetion.BadRequestCustomException;
import com.anderson.core.model.Operation;
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
class ValidateTransactionOperationTest {

    private ValidateTransactionOperation validateTransactionOperation;

    @BeforeEach
    public void init(){
        validateTransactionOperation = new ValidateTransactionOperation();
    }


    @Test
    @DisplayName("Test Transaction operation Success")
    public void testTransactionOperationSuccess(){
        try {

            Transaction transaction = Transaction.builder()
                    .operation(Operation.SAQUE)
                    .build();

            validateTransactionOperation.validate(transaction);

        }catch (Exception e){
            e.printStackTrace();
            fail("Fail test");
        }
    }

    @Test
    @DisplayName("Test Transaction operation invalid")
    public void testTransactionOperationInvalid(){
        try {
            Transaction transaction = Transaction.builder()
                    .amount(new BigDecimal("150.00"))
                    .build();

            validateTransactionOperation.validate(transaction);
            fail("Fail test");
        }catch (BadRequestCustomException e){
            Assertions.assertEquals("Operation don't informed", e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            fail("Fail test");
        }

    }


}