package org.polytech.spring;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.polytech.spring.exceptions.CreationException;
import org.polytech.spring.exceptions.UpdateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FilmController {
    
    private final FilmService service;

    public FilmController(FilmService s) {
        this.service = s;
    }

    @GetMapping("/films")
    public List<Film> getAll() {
        return service.findAll();
    }

    @PostMapping("/films")
    public ResponseEntity<Void> create(@RequestBody Map<String, Object> payload) {

        String titre = (String) payload.get("titre");
        String realisateur = (String) payload.get("realisateur");
        Object RawDateSortie = payload.get("dateSortie");
        Object RawGenre = payload.get("genre");

        if (titre == null || titre.isBlank()) {
            throw new CreationException("Le titre est obligatoire");
        }
        if (realisateur == null || realisateur.isBlank()) {
            throw new CreationException("Le réalisateur est obligatoire");
        }
        if (RawDateSortie == null) {
            throw new CreationException("La date de sortie est obligatoire");
        }
        if (RawGenre == null) {
            throw new CreationException("Le genre est obligatoire");
        }

        LocalDate dateSortie;
        try {
            dateSortie = LocalDate.parse((String) RawDateSortie);
        } catch (Exception e) {
            throw new CreationException("Format de date invalide (attendu: AAAA-MM-JJ)");
        }

        Genre genre;
        try {
            genre = Genre.valueOf((String) RawGenre);
        } catch (Exception e) {
            throw new CreationException("Genre inconnu : " + RawGenre);
        }

        long id = service.create(titre, realisateur, dateSortie, genre);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/films/{id:\\d+}")
    public Film getOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PutMapping("/films/{id:\\d+}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody Map<String, Object> payload) {

        String titre = (String) payload.get("titre");
        String realisateur = (String) payload.get("realisateur");
        Object RawDateSortie = payload.get("dateSortie");
        Object RawGenre = payload.get("genre");

        if (titre == null || titre.isBlank()) {
            throw new UpdateException("Le titre est obligatoire");
        }
        if (realisateur == null || realisateur.isBlank()) {
            throw new UpdateException("Le réalisateur est obligatoire");
        }
        if (RawDateSortie == null) {
            throw new UpdateException("La date de sortie est obligatoire");
        }
        if (RawGenre == null) {
            throw new UpdateException("Le genre est obligatoire");
        }

        LocalDate dateSortie;
        try {
            dateSortie = LocalDate.parse((String) RawDateSortie);
        } catch (Exception e) {
            throw new UpdateException("Format de date invalide (attendu: AAAA-MM-JJ)");
        }

        Genre genre;
        try {
            genre = Genre.valueOf((String) RawGenre);
        } catch (Exception e) {
            throw new UpdateException("Genre inconnu : " + RawGenre);
        }

        service.update(id, titre, realisateur, dateSortie, genre);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("films/{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
