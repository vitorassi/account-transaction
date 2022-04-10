package com.anderson.core.model;

import com.anderson.common.excpetion.BadRequestCustomException;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum Operation {

    COMPRA_A_VISTA(1),
    COMPRA_PARCELADA(2),
    SAQUE(3),
    PAGAMENTO(4);

    private final int value;

    Operation(int value) {
        this.value = value;
    }

    public static Operation valueOf(int value) {
        return Stream.of(Operation.values())
                .filter(operation -> operation.getValue() == value)
                .findFirst().orElseThrow(BadRequestCustomException::new);
    }

    public static Operation valueOfByName(String name) {
        return Stream.of(Operation.values())
                .filter(operation -> operation.name().equals(name))
                .findFirst().orElseThrow(() -> new BadRequestCustomException("Invalid enum Operation"));
    }

}
