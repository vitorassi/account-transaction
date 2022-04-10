package com.anderson.common.excpetion;

import com.anderson.common.model.DetailException;
import lombok.Getter;

public class CustomException extends RuntimeException {

    @Getter
    private DetailException model;

    public CustomException(int code, String message) {
        super(message);
        this.model = DetailException.builder()
                .code(code)
                .message(message).build();
    }
}
