package org.polytech.spring;

import java.net.URI;
import java.time.Instant;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class ApiExceptionHandler {
    
    @ExceptionHandler(FilmNotFoundException.class)
    public ProblemDetail handle(FilmNotFoundException e) {
        ProblemDetail pb = ProblemDetail.forStatusAndDetail(BAD_REQUEST,e.getMessage());
        pb.setTitle("Titre invalide");
        pb.setType(URI.create(
        "https://api.polytech.fr/errors/patient"
        ));
        pb.setProperty("timestamp", Instant.now());
        return pb
}
