INSERT INTO acteur (id, nom, prenom) VALUES (3, 'DiCaprio', 'Leonardo');
INSERT INTO acteur (id, nom, prenom) VALUES (4, 'Gordon-Levitt', 'Joseph');
INSERT INTO acteur (id, nom, prenom) VALUES (5, 'Neill', 'Sam');
INSERT INTO acteur (id, nom, prenom) VALUES (6, 'Dern', 'Laura');
INSERT INTO acteur (id, nom, prenom) VALUES (7, 'Stone', 'Emma');
INSERT INTO acteur (id, nom, prenom) VALUES (8, 'Gosling', 'Ryan');
INSERT INTO acteur (id, nom, prenom) VALUES (9, 'Lawrence', 'Jennifer');
INSERT INTO acteur (id, nom, prenom) VALUES (10, 'Hutcherson', 'Josh');

INSERT INTO film (id, titre, realisateur, date_sortie, genre)
VALUES (2, 'Inception', 'Christopher Nolan', '2010-07-21', 'SCIENCE_FICTION');

INSERT INTO film (id, titre, realisateur, date_sortie, genre)
VALUES (3, 'Jurassic Park', 'Steven Spielberg', '1993-10-20', 'ACTION');

INSERT INTO film (id, titre, realisateur, date_sortie, genre)
VALUES (4, 'Lalaland', 'Damien Chazelle', '2017-01-25', 'ROMANCE');

INSERT INTO film (id, titre, realisateur, date_sortie, genre)
VALUES (5, 'Hunger Games', 'Gary Ross', '2012-03-21', 'SCIENCE_FICTION');

INSERT INTO film_acteurs (id_film, id_acteur) VALUES (2, 3);
INSERT INTO film_acteurs (id_film, id_acteur) VALUES (2, 4);

INSERT INTO film_acteurs (id_film, id_acteur) VALUES (3, 5);
INSERT INTO film_acteurs (id_film, id_acteur) VALUES (3, 6);

INSERT INTO film_acteurs (id_film, id_acteur) VALUES (4, 7);
INSERT INTO film_acteurs (id_film, id_acteur) VALUES (4, 8);

INSERT INTO film_acteurs (id_film, id_acteur) VALUES (5, 9);
INSERT INTO film_acteurs (id_film, id_acteur) VALUES (5, 10);