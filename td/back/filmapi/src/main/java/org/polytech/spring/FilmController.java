package org.polytech.spring;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmapi")
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
    public String create() {
        return "Hello world!";
    }

    @GetMapping("/films/{id:\\d+}")
    public Film getOne(@PathVariable Integer id) {
        return service.findOne(id);
    }

    @PutMapping("/films/{id:\\d+}")
    public void update(@PathVariable Integer id) {
    }

    @DeleteMapping("films/{id:\\d+}")
    public void delete(@PathVariable Integer id) {
    }
}
