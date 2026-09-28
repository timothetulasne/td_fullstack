package org.polytech.spring;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service 
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository r) {
        this.filmRepository = r;
    }

    public List<FilmDto> findAll() {
        return filmRepository.findAll().stream().map(FilmMapper::toDto).toList();
    }

    public List<FilmDto> findByTitre(String titre) {
        String searchTitre = ObjectUtils.isEmpty(titre) ? "%" : titre;
        return filmRepository.findByTitre(searchTitre).stream().map(FilmMapper::toDto).toList();
    }
}
