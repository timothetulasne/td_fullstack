package org.polytech.spring;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Repository;

@Repository 
public class FilmRepository {
    
    private final List<Film> liste = new ArrayList<>();

    public List<Film> findAll() {
        return this.liste;
    }

    public Film findOne(Long id) {
    for (Film film : this.liste) {
        if (Objects.equals(film.getId(), id)) {
            return film;
        }
    }
    return null;
    }

    public Long create(String t, String r, LocalDate d, Genre g) {
        Film film = new Film(t,r,d,g);
        long id;
        if (liste == null || liste.isEmpty()) {
            id = 1;
        } else {
            id = liste.getLast().getId() + 1;
        }
        film.setId(id);
        liste.add(film);
        return id;
    }

    public void delete(Long id) {
        liste.removeIf(film -> Objects.equals(film.getId(), id));
    }

    public Film update(Long id, String t, String r, LocalDate d, Genre g) {
        Film film = findOne(id);

        film.setTitre(t);
        film.setRealisateur(r);
        film.setDateSortie(d);
        film.setGenre(g);

        return film;
    }
}
