package org.polytech.spring;

import java.net.URI;
import java.time.Instant;

import org.polytech.spring.exceptions.ActeurNotFoundException;
import org.polytech.spring.exceptions.CreationException;
import org.polytech.spring.exceptions.FilmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class ApiExceptionHandler {

    @ExceptionHandler(CreationException.class)
    public ProblemDetail handleCreation(CreationException e) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        pb.setTitle("Création invalide");
        pb.setType(URI.create("/errors/creation"));
        pb.setProperty("timestamp", Instant.now());
        return pb;
    }

    @ExceptionHandler(FilmNotFoundException.class)
    public ProblemDetail handleNotFound(FilmNotFoundException e) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pb.setTitle("Film non trouvé");
        pb.setType(URI.create("/errors/film-not-found"));
        pb.setProperty("timestamp", Instant.now());
        return pb;
    }

    @ExceptionHandler({ IllegalArgumentException.class, NullPointerException.class })
    public ProblemDetail handleBadRequest(RuntimeException e) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Requête invalide ou données manquantes");
        pb.setTitle("Données invalides");
        pb.setType(URI.create("/errors/bad-request"));
        pb.setProperty("timestamp", Instant.now());
        return pb;
    }

    @ExceptionHandler(ActeurNotFoundException.class)
    public ProblemDetail handleActeurNotFound(ActeurNotFoundException e) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        pb.setTitle("Acteur non trouvé");
        pb.setType(URI.create("/errors/acteur-not-found"));
        pb.setProperty("timestamp", Instant.now());
        return pb;
    }
}
