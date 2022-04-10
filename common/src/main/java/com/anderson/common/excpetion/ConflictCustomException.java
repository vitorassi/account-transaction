package com.anderson.common.excpetion;


public class ConflictCustomException extends CustomException {

    private final static int CODE = 409;

    public ConflictCustomException(String message) {
        super(CODE, message);
    }

    public ConflictCustomException() {
        super(CODE, "RESOURCE CONFLICT");
    }
}
