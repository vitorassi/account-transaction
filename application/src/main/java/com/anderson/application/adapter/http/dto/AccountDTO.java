package com.anderson.application.adapter.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private String id;

    private String documentNumber;

    private BigDecimal availableCreditLimit;

}
