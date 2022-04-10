package com.anderson.application.adapter.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private UUID transactionId;
    private UUID accountId;
    private String operation;
    private BigDecimal amount;
    private LocalDateTime eventDate;
}
