package org.polytech.spring.exceptions;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(Long id) {
        super("Film inconnu avec id : "+id);
    }
}
