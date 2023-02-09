package com.bees.services.exceptions;

public class ClasseUtilitariaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClasseUtilitariaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClasseUtilitariaException(String message) {
        super(message);
    }
}
