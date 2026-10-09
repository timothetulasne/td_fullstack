## Structure

    tp/
      back/     projet Gradle + Spring Boot préconfiguré : TP Java / Spring
      front/    répertoire vide, destiné au projet créé par « ng new » : TP Angular
    td/
      back/     TD : API REST de la bibliothèque de films
        http/   requêtes HTTP, exécutées avec l'extension VSCode REST Client
      front/    TD : front Angular de la bibliothèque de films

## Récupération du dépôt

```bash
git clone https://github.com/timothetulasne/td_fullstack mon-depot
cd mon-depot
git remote remove origin       
git remote add origin https://github.com/timothetulasne/td_fullstack
git push -u origin main
```

## Démarrage

### Back du TD

```bash
cd td/back
./gradlew build      
./gradlew bootRun
```

### Front du TD

```bash
cd td/front
ng new tp-front 
```

## Rendus

| Tag   | Contenu                                        |
|-------|------------------------------------------------|
| `td1` | API REST, stockage en mémoire                  |
| `td2` | persistance JPA, DTO, CORS                     |
| `td3` | front Angular branché sur l'API                |

### Commentaires

Projet presque fini. Il manque les fonctionnalités pour ajouter et supprimer des acteurs aux films, ainsi que leur création et suppression à la bdd. 

Ma base de donne sur Postgresql est nommé TD2

Pas trop de souci sur le back car déja fait du spring l'annee dernière et bien compris comment ça fonctionne

Pour ce qui est du front, plus compliqué.

## Problèmes rencontrés

Impossible de récupérer les films des acteurs mais l'inverse est possible. Corriger en fin de séance avec l'outils forkjoin pour avoir double information sur un signal
