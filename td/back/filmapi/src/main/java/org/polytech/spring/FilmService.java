package org.polytech.spring;

import java.time.LocalDate;
import java.util.List;

import org.polytech.spring.exceptions.FilmNotFoundException;
import org.springframework.stereotype.Service;

@Service 
public class FilmService {

    private final FilmRepository repository;

    public FilmService(FilmRepository r) {
        this.repository = r;
    }

    public List<Film> findAll() {
        return this.repository.findAll();
    }

    public Film findOne(Long id) {
        if (this.repository.findOne(id) == null) {
            throw new FilmNotFoundException(id);
        }
        return this.repository.findOne(id);
    }

    public long create(String t, String r, LocalDate d, Genre g) {
        Long id = this.repository.create(t,r,d,g);
        return id;
    }

    public void update(Long id, String t, String r, LocalDate d, Genre g) {
        this.repository.update(id,t,r,d,g);
    }
    public void delete(Long id) {
        this.repository.delete(id);
    }
}
