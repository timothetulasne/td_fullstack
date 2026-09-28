package org.polytech.spring;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/titre")
    public List<FilmDto> findByTitre(String titre) {
        return service.findByTitre(titre);
    }
}
