package com.anderson.application.adapter.http.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class AccountCreateDTO {

    @Valid
    @Pattern(regexp = "[0-9]+", message = "documentNumber Invalid")
    private String documentNumber;

    private BigDecimal availableCreditLimit;

}
