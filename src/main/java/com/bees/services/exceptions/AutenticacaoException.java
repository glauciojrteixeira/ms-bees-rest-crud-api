package com.bees.services.exceptions;

public class AutenticacaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AutenticacaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutenticacaoException(String message) {
        super(message);
    }
}
