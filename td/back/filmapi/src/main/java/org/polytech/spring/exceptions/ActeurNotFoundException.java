package org.polytech.spring.exceptions;

public class ActeurNotFoundException extends RuntimeException {
    public ActeurNotFoundException() {
        super("Acteur inexistant");
    }
}