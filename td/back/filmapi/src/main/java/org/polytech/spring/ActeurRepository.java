package org.polytech.spring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActeurRepository extends JpaRepository<Acteur, Long> {
    
    List<Acteur> findByFilms_Id(Long filmId);

    @Query("SELECT a FROM Acteur a JOIN a.films f WHERE f.id = :filmId")
    List<Acteur> findActeursByFilmIdQuery(@Param("filmId") Long filmId);
}