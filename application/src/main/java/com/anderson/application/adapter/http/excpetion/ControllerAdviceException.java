package com.anderson.application.adapter.http.excpetion;

import com.anderson.common.excpetion.CustomException;
import com.anderson.common.model.DetailException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ValidationException;


@ControllerAdvice
@Slf4j
public class ControllerAdviceException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<DetailException> handleNotExceptions(CustomException e) {
        HttpStatus status = HttpStatus.valueOf(e.getModel().getCode());

        return new ResponseEntity<>(e.getModel(), status);
    }

    @ExceptionHandler({Exception.class, NullPointerException.class})
    public ResponseEntity<DetailException> handleExceptions(Exception e) {
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(DetailException.builder()
                .code(500)
                .message("INTERNAL ERROR").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ValidationException.class, ValidationException.class})
    public ResponseEntity<DetailException> handleExceptions(ValidationException e) {
        return new ResponseEntity<>(DetailException.builder()
                .code(400)
                .message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<DetailException> handleExceptions(BindException e) {
        return new ResponseEntity<>(DetailException.builder()
                .code(400)
                .message(e.getFieldError().getDefaultMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<DetailException> handleExceptions(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(DetailException.builder()
                .code(400)
                .message("Cannot deserialize body").build(), HttpStatus.BAD_REQUEST);
    }


}
