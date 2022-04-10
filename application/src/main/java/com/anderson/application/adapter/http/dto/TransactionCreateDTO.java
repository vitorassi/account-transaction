package com.anderson.application.adapter.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreateDTO {

    private UUID accountId;
    private String operation;
    private BigDecimal amount;

}
