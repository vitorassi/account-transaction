package com.anderson.application.adapter.http.dto;

import com.anderson.common.excpetion.BadRequestCustomException;
import io.swagger.annotations.Api;

import java.util.stream.Stream;

public enum OperationDto {

    COMPRA_A_VISTA,
    COMPRA_PARCELADA,
    SAQUE,
    PAGAMENTO;

    public static OperationDto valueOfByName(String name) {
        return Stream.of(OperationDto.values())
                .filter(operation -> operation.name().equals(name))
                .findFirst().orElseThrow(() -> new BadRequestCustomException("Invalid enum Operation"));
    }

}
