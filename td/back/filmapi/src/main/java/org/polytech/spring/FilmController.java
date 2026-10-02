package org.polytech.spring;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.polytech.spring.exceptions.FilmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/films")
public class FilmController {
    
    private final FilmService service;

    public FilmController(FilmService s) {
        this.service = s;
    }

    @GetMapping
    public List<FilmDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody FilmCreationDto film) {
        
        Long id = service.create(film); 

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public FilmDto findById(@PathVariable Long id) {
        Optional<FilmDto> film = service.findById(id);
        if (film.isEmpty()) {
            throw new FilmNotFoundException();
        }
        return film.get();
    }

    @PutMapping("/{id}")
    public FilmDto update(@PathVariable Long id, @RequestBody FilmCreationDto f) {
        Optional<FilmDto> film = service.findById(id);
        if (film.isEmpty()) {
            throw new FilmNotFoundException();
        }
        service.update(id, f);
        return service.findById(id).get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/acteurs")
    public List<ActeurDto> findActeursByFilmId(@PathVariable Long id) {
        return service.findActeursByFilmId(id); 
    }

    @PostMapping("/{id}/acteurs/{acteurId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addActeurToFilm(@PathVariable Long id, @PathVariable Long acteurId) {
        service.addActeur(id, acteurId);
    }

    @DeleteMapping("/{id}/acteurs/{acteurId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeActeurFromFilm(@PathVariable Long id, @PathVariable Long acteurId) {
        service.removeActeur(id, acteurId);
    }
}
