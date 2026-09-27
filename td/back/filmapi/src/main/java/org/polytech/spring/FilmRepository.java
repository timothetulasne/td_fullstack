package org.polytech.spring;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository 
public class FilmRepository {
    
    public static List<Film> liste;

    public FilmRepository(List<Film> liste) {
        this.liste = liste;
    }

    public List<Film> findAll() {
        return this.liste;
    }

    public Film findOne(int id) {
    for (Film film : this.liste) {
        if (film.getId() != null && film.getId() == id) {
            return film;
        }
    }
    return null;
    }

}
