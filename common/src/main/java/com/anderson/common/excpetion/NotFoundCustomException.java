package com.anderson.common.excpetion;


public class NotFoundCustomException extends CustomException {

    private final static int CODE = 404;

    public NotFoundCustomException() {
        super(CODE, "DATA NOT FOUND");
    }
}
