package org.polytech.spring;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

enum Genre {
    SCIENCE_FICTION, ACTION, THRILLER, ROMANCE, DRAME, COMEDIE
}

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 200)
    private String titre;

    @Column(nullable = false, length = 80)
    private String realisateur;

    @Column(name = "date_sortie", nullable = false)
    private LocalDate dateSortie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @ManyToMany
    @JoinTable(
        name = "film_acteurs",
        joinColumns = @JoinColumn(name = "id_film"),
        inverseJoinColumns = @JoinColumn(name = "id_acteur")
    )
    private Set<Acteur> acteurs = new HashSet<>();

    public Film() {
    }

    public Film(String titre, String realisateur, LocalDate dateSortie, Genre genre) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.dateSortie = dateSortie;
        this.genre = genre;
    }

    public void addActeur(Acteur acteur) {
        this.acteurs.add(acteur);
        acteur.getFilms().add(this);
    }

    public void removeActeur(Acteur acteur) {
        this.acteurs.remove(acteur);
        acteur.getFilms().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public LocalDate getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Acteur> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Set<Acteur> acteurs) {
        this.acteurs = acteurs;
    }
}