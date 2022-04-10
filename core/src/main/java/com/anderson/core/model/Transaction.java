package com.anderson.core.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class Transaction {

    private UUID transactionId;
    private UUID accountId;
    private Operation operation;
    private BigDecimal amount;
    private LocalDateTime eventDate;

}
