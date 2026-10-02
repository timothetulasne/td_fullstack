package org.polytech.spring;

import java.util.List;
import java.util.Optional;

import org.polytech.spring.exceptions.CreationException;
import org.polytech.spring.exceptions.ActeurNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActeurService {

    private final ActeurRepository acteurRepository;
    private final FilmRepository filmRepository;

    public ActeurService(ActeurRepository acteurRepository, FilmRepository filmRepository) {
        this.acteurRepository = acteurRepository;
        this.filmRepository = filmRepository;
    }

    @Transactional(readOnly = true)
    public List<ActeurDto> findAll() {
        return acteurRepository.findAll().stream().map(ActeurMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Optional<ActeurDto> findById(Long id) {
        return acteurRepository.findById(id).map(ActeurMapper::toDto);
    }

    public Long create(ActeurCreationDto dto) {
        if (dto == null || dto.nom() == null || dto.nom().isBlank() || dto.prenom() == null || dto.prenom().isBlank()) {
            throw new CreationException();
        }
        Acteur acteur = new Acteur(dto.nom(), dto.prenom());
        return acteurRepository.save(acteur).getId();
    }

    @Transactional
    public Optional<ActeurDto> update(Long id, ActeurCreationDto dto) {
        Optional<Acteur> optionalActeur = acteurRepository.findById(id);
        if (optionalActeur.isEmpty()) return Optional.empty();

        Acteur acteur = optionalActeur.get();
        acteur.setNom(dto.nom());
        acteur.setPrenom(dto.prenom());

        return Optional.of(ActeurMapper.toDto(acteur));
    }

    public void delete(Long id) {
        if (!acteurRepository.existsById(id)) {
            throw new ActeurNotFoundException();
        }
        acteurRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<FilmDto> findFilmsByActeurId(Long acteurId) {
        if (!acteurRepository.existsById(acteurId)) {
            throw new ActeurNotFoundException();
        }
        // Utilise la requête de l'exercice précédent
        return filmRepository.findByActeurs_Id(acteurId).stream()
            .map(FilmMapper::toDto)
            .toList();
    }
}