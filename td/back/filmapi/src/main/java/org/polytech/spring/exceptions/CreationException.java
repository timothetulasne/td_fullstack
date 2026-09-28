package org.polytech.spring.exceptions;

public class CreationException extends RuntimeException {
    public CreationException() {
        super("données invalides ou incomplètes");
    }
}
