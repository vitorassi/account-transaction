package com.anderson.core.service.internal;

import java.util.List;

public abstract class AbstractionValidation<T> implements Validation<T> {

    protected List<Validation<T>> validations;

    public void validate(T t) {
        validations.forEach(transaction -> transaction.validate(t));
    }
}
