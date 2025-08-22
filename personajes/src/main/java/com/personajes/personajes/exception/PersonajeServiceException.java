package com.personajes.personajes.exception;

public class PersonajeServiceException extends RuntimeException {

    public PersonajeServiceException(String message) {
        super(message);
    }

    public PersonajeServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
