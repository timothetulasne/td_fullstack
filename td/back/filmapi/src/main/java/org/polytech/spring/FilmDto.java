package org.polytech.spring;

import java.time.LocalDate;

public record FilmDto (
    String titre,
    String realisateur,
    LocalDate dateSortie,
    Genre genre
) {}
