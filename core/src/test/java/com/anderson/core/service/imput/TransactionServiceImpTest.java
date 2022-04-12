package com.anderson.core.service.imput;


import com.anderson.core.model.Operation;
import com.anderson.core.model.Transaction;
import com.anderson.core.port.input.TransactionService;
import com.anderson.core.port.internal.CalculateTransactionService;
import com.anderson.core.service.internal.Validation;
import com.anderson.core.service.out.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImpTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CalculateTransactionService calculateTransactionService;

    @Mock
    private Validation validation;

    private TransactionService transactionService;

    @BeforeEach
    public void init() {
        transactionService = new TransactionServiceImp(transactionRepository, validation, calculateTransactionService);
    }

    @Test
    public void testSave() {
        transactionService.save(Transaction.builder()
                .amount(new BigDecimal("1500.00"))
                .operation(Operation.SAQUE)
                .accountId(UUID.randomUUID())
                .build());
        verify(validation, times(1)).validate(any(Transaction.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

}