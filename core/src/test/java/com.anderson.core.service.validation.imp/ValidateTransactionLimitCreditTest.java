package com.anderson.core.service.validation.imp;


import com.anderson.common.excpetion.ConflictCustomException;
import com.anderson.core.model.Account;
import com.anderson.core.model.Operation;
import com.anderson.core.model.Transaction;
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
class ValidateTransactionLimitCreditTest {
    @Mock
    private AccountRepository accountRepository;

    private ValidateTransactionLimitCredit validateTransactionLimitCredit;

    @BeforeEach
    public void init() {
        validateTransactionLimitCredit = new ValidateTransactionLimitCredit(accountRepository);
    }


    @Test
    @DisplayName("Test Transaction credit limit Success")
    public void testTransactionCreditLimitPositive() {
        try {
            UUID accountId = UUID.randomUUID();

            Account account = Account.builder().id(accountId)
                    .documentNumber("1234")
                    .availableCreditLimit(new BigDecimal("1500.00"))
                    .build();

            when(accountRepository.findBy(accountId))
                    .thenReturn(Optional.of(account));


            Transaction transaction = Transaction.builder()
                    .amount(new BigDecimal("150.00"))
                    .accountId(account.getId())
                    .operation(Operation.COMPRA_A_VISTA)
                    .build();

            validateTransactionLimitCredit.validate(transaction);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Fail test");
        }
    }


    @Test
    @DisplayName("Test Transaction credit limit equals")
    public void testTransactionCreditLimitEquals() {
        try {
            UUID accountId = UUID.randomUUID();

            Account account = Account.builder().id(accountId)
                    .documentNumber("1234")
                    .availableCreditLimit(new BigDecimal("1500.00"))
                    .build();

            when(accountRepository.findBy(accountId))
                    .thenReturn(Optional.of(account));


            Transaction transaction = Transaction.builder()
                    .amount(new BigDecimal("1500.00"))
                    .accountId(account.getId())
                    .operation(Operation.COMPRA_A_VISTA)
                    .build();

            validateTransactionLimitCredit.validate(transaction);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Fail test");
        }
    }


    @Test
    @DisplayName("Test Transaction credit limit negative")
    public void testTransactionCreditLimitNegative() {
        try {
            UUID accountId = UUID.randomUUID();

            Account account = Account.builder().id(accountId)
                    .documentNumber("1234")
                    .availableCreditLimit(new BigDecimal("1501.00"))
                    .build();

            when(accountRepository.findBy(accountId))
                    .thenReturn(Optional.of(account));


            Transaction transaction = Transaction.builder()
                    .amount(new BigDecimal("1500.00"))
                    .accountId(account.getId())
                    .operation(Operation.COMPRA_A_VISTA)
                    .build();

            validateTransactionLimitCredit.validate(transaction);

        } catch (ConflictCustomException e) {
            Assertions.assertEquals("Conflict \"Account\" don't exist Limit Credit", e.getMessage());
        } catch (Exception e) {
            fail("Fail test");
            e.printStackTrace();
        }
    }

//
//
//    @Test
//    @DisplayName("Test Account not informed")
//    public void testAccountNotInformed() {
//        try {
//            Transaction transaction = Transaction.builder()
//                    .amount(new BigDecimal("150.00"))
//                    .operation(Operation.COMPRA_A_VISTA)
//                    .build();
//            validateTransactionAccount.validate(transaction);
//
//            fail("Fail test");
//
//        } catch (ConflictCustomException e) {
//            Assertions.assertEquals(ConflictCustomException.class, e.getClass());
//            Assertions.assertEquals("Conflict \"Account\" don't exist in database", e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Fail test");
//        }
//    }
//
//    @Test
//    @DisplayName("Test Account If Exist")
//    public void testAccountNotExist() {
//        try {
//
//            Account account = Account.builder().id(UUID.randomUUID())
//                    .documentNumber("1234").build();
//
//            Transaction transaction = Transaction.builder()
//                    .amount(new BigDecimal("150.00"))
//                    .accountId(account.getId())
//                    .operation(Operation.COMPRA_A_VISTA)
//                    .build();
//            validateTransactionAccount.validate(transaction);
//
//            fail("Fail test");
//
//        } catch (ConflictCustomException e) {
//            Assertions.assertEquals(ConflictCustomException.class, e.getClass());
//            Assertions.assertEquals("Conflict \"Account\" don't exist in database", e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            fail("Fail test");
//        }
//    }

}