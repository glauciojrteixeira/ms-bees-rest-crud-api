package com.bees.services.exceptions;

public class AutorizacaoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AutorizacaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutorizacaoException(String message) {
        super(message);
    }
}
