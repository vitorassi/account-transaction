package com.anderson.core.service.internal;

import com.anderson.core.model.Account;
import com.anderson.core.model.Operation;
import com.anderson.core.model.Transaction;
import com.anderson.core.port.internal.CalculateTransactionService;
import com.anderson.core.service.validation.imp.ValidateAccountDocument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculateTransactionServiceImpTest {


    private CalculateTransactionService calculateTransactionService;
    @BeforeEach
    public void init(){
        calculateTransactionService = new CalculateTransactionServiceImp();
    }

    @Test
    @DisplayName("Test Calculate Transaction negative")
    public void testCalculateTransactionNegative(){
        try {

            Transaction transaction = Transaction.builder()
                    .operation(Operation.COMPRA_A_VISTA)
                    .amount(new BigDecimal("9874.14"))
                    .build();

            calculateTransactionService.calculate(transaction);

            Assertions.assertEquals(new BigDecimal("-9874.14"), transaction.getAmount());

        }catch (Exception e){
            e.printStackTrace();
            fail("Fail test");
        }
    }

    @Test
    @DisplayName("Test Calculate Transaction positive")
    public void testCalculateTransactionPositive(){
        try {

            Transaction transaction = Transaction.builder()
                    .operation(Operation.PAGAMENTO)
                    .amount(new BigDecimal("7721.14"))
                    .build();

            calculateTransactionService.calculate(transaction);

            Assertions.assertEquals(new BigDecimal("7721.14"), transaction.getAmount());

        }catch (Exception e){
            e.printStackTrace();
            fail("Fail test");
        }
    }

}