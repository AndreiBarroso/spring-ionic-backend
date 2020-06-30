package com.andreibarroso.springionic.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }
    public List<FieldMessage> getList() {
        return  errors;
    }

    public void addError (String fieldName, String messagem) {
        errors.add(new FieldMessage(fieldName, messagem));
    }

}