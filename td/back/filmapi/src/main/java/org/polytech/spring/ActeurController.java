package org.polytech.spring;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.polytech.spring.exceptions.ActeurNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/acteurs")
public class ActeurController {

    private final ActeurService service;

    public ActeurController(ActeurService service) {
        this.service = service;
    }

    @GetMapping
    public List<ActeurDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ActeurDto findById(@PathVariable Long id) {
        return service.findById(id).orElseThrow(ActeurNotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ActeurCreationDto acteur) {
        Long id = service.create(acteur);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ActeurDto update(@PathVariable Long id, @RequestBody ActeurCreationDto a) {
        return service.update(id, a).orElseThrow(ActeurNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/films")
    public List<FilmDto> findFilmsByActeurId(@PathVariable Long id) {
        return service.findFilmsByActeurId(id);
    }
}