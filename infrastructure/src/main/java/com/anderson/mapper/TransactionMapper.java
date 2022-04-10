package com.anderson.mapper;

import com.anderson.adapter.database.entity.TransactionEntity;
import com.anderson.core.model.Operation;
import com.anderson.core.model.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static TransactionEntity toTransactionEntity(Transaction transcation) {
        return TransactionEntity.builder()
                .accountId(transcation.getAccountId())
                .amount(transcation.getAmount())
                .operationType(transcation.getOperation().getValue())
                .eventDate(transcation.getEventDate())
                .build();
    }

    public static Transaction toTransaction(TransactionEntity transactionEntity) {
        return Transaction.builder()
                .transactionId(transactionEntity.getId())
                .accountId(transactionEntity.getAccountId())
                .amount(transactionEntity.getAmount())
                .operation(Operation.valueOf(transactionEntity.getOperationType()))
                .eventDate(transactionEntity.getEventDate())
                .build();
    }
}
