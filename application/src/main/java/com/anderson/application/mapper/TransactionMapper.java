package com.anderson.application.mapper;

import com.anderson.application.adapter.http.dto.TransactionCreateDTO;
import com.anderson.application.adapter.http.dto.TransactionDTO;
import com.anderson.core.model.Operation;
import com.anderson.core.model.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionMapper {

    public static TransactionDTO transactionDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .transactionId(transaction.getTransactionId())
                .accountId(transaction.getAccountId())
                .amount(transaction.getAmount())
                .operation(transaction.getOperation().name())
                .eventDate(transaction.getEventDate())
                .build();
    }

    public static Transaction toTransaction(TransactionCreateDTO transactionDTO) {
        return Transaction.builder()
                .accountId(transactionDTO.getAccountId())
                .amount(transactionDTO.getAmount())
                .operation(Operation.valueOfByName(transactionDTO.getOperation()))
                .build();
    }

}
