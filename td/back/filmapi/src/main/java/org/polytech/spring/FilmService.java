package org.polytech.spring;

import java.util.List;
import java.util.Optional;

import org.polytech.spring.exceptions.CreationException;
import org.polytech.spring.exceptions.FilmNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service 
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository r) {
        this.filmRepository = r;
    }

    public List<FilmDto> findAll() {
        return filmRepository.findAll().stream().map(FilmMapper::toDto).toList();
    }

    public Optional<FilmDto> findById(Long id) {
        return filmRepository.findById(id).map(FilmMapper::toDto);
    }

    public Long create(FilmCreationDto film) {
        if (film == null) {
            throw new CreationException();
        }
        if (film.titre() == null || film.titre().isBlank() || film.realisateur() == null || film.realisateur().isBlank() || film.dateSortie() == null || film.genre() == null) {
            throw new CreationException();
        }
    Film entity = FilmMapper.toEntity(film);
    Film savedEntity = filmRepository.save(entity);
    return savedEntity.getId();
}

    @Transactional
    public Optional<FilmDto> update(Long id, FilmCreationDto dto) {
        Optional<Film> optionalFilm = filmRepository.findById(id);

        if (optionalFilm.isEmpty()) {
            return Optional.empty();
        }

        Film film = optionalFilm.get();
        film.setTitre(dto.titre());
        film.setRealisateur(dto.realisateur());
        film.setDateSortie(dto.dateSortie());
        film.setGenre(dto.genre());

        FilmDto filmDto = FilmMapper.toDto(film);
        return Optional.of(filmDto);
    }

    public void delete(Long id) {
        if (!filmRepository.existsById(id)) {
            throw new FilmNotFoundException();
        }
        filmRepository.deleteById(id);
    }
}
