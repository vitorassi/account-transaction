package com.anderson.common.excpetion;


public class BadRequestCustomException extends CustomException {

    private final static int CODE = 400;

    public BadRequestCustomException(String message) {
        super(CODE, message);
    }

    public BadRequestCustomException() {
        super(CODE, "BAD REQUEST");
    }
}
