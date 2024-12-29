package com.isadora.backoffice.seguranca.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationException extends IllegalArgumentException {
    private String invalidValue;

    public ValidationException(String s, String invalidValue) {
        super(s);
        this.invalidValue = invalidValue;
    }
}
