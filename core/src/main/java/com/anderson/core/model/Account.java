package com.anderson.core.model;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@EqualsAndHashCode
public class Account {

    private UUID id;
    private String documentNumber;
    private BigDecimal availableCreditLimit;
}
