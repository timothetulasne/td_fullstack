package org.polytech.spring;

import java.util.List;

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

    public Film findOne(int id) {
        return this.repository.findOne(id);
    }
    
}
