package org.polytech.spring.exceptions;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException() {
        super("Film inexistant");
    }
}
