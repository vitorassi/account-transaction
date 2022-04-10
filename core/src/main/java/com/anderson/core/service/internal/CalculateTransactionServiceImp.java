package com.anderson.core.service.internal;

import com.anderson.core.model.Operation;
import com.anderson.core.model.Transaction;
import com.anderson.core.port.internal.CalculateTransactionService;

import java.math.BigDecimal;
import java.util.Map;

public class CalculateTransactionServiceImp implements CalculateTransactionService {

    private final BigDecimal FACTOR_POSITIVE = BigDecimal.ONE;
    private final BigDecimal FACTOR_NEGATIVE = new BigDecimal("-1.00");

    private final Map<Operation, BigDecimal> factors
            = Map.of(
            Operation.COMPRA_A_VISTA, FACTOR_NEGATIVE,
            Operation.COMPRA_PARCELADA, FACTOR_NEGATIVE,
            Operation.PAGAMENTO, FACTOR_POSITIVE,
            Operation.SAQUE, FACTOR_NEGATIVE
    );

    @Override
    public void calculate(Transaction transcation) {

        BigDecimal factory = factors.get(transcation.getOperation());
        transcation.setAmount(transcation.getAmount().multiply(factory));

    }

}
