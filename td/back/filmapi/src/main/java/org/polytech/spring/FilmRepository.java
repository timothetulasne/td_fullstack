package org.polytech.spring;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findAll();

    List<Film> findByTitre(String titre);

    List<Film> findByRealisateur(String realisateur);
    
    List<Film> findByDateSortie(LocalDate dateSortie);

    List<Film> findByGenre(Genre genre);
}
