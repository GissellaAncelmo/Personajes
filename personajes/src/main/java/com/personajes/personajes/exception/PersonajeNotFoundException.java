package com.personajes.personajes.exception;

public class PersonajeNotFoundException extends RuntimeException {

    public PersonajeNotFoundException(String message) {
        super(message);
    }

    public PersonajeNotFoundException(Long id) {
        super("Personaje con ID " + id + " no encontrado");
    }

    public PersonajeNotFoundException(String campo, String valor) {
        super("Personaje con " + campo + " '" + valor + "' no encontrado");
    }
}