package com.personajes.personajes.exception;

public class PersonajeAlreadyExistsException extends RuntimeException {

    public PersonajeAlreadyExistsException(String identificacion) {
        super("Ya existe un personaje con la identificación: " + identificacion);
    }
}