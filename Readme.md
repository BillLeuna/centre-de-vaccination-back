# Centre de Vaccination


## Introduction

Le backend du projet Centre de Vaccination est développé en utilisant Java avec le framework Spring Boot. Il expose des API REST pour gérer les fonctionnalités relatives aux centres de vaccination, aux administrateurs, aux médecins, aux réservations, etc.

Lien vers le front-end : https://github.com/BillLeuna/centre-de-vaccination-front.git


## Technologies Utilisées
   
   - Java 11: Langage de programmation principal. 
   - Spring Boot: Cadre de développement pour la création d'applications Java. 
   - Spring Data JPA: Couche d'abstraction pour l'accès aux données avec le modèle de programmation Java Persistence API (JPA). 
   - Spring MVC: Module pour la création de services Web RESTful. 
   - PostgreSQL: Système de gestion de base de données relationnelle. 
   - JUnit5 et Mockito: Outils de test.

## Structure du Projet

Le backend suit une architecture modulaire avec les composants suivants :


- Contrôleurs (Controllers): Gèrent les requêtes HTTP et appellent les services appropriés.
- Services: Contiennent la logique métier.
- Entités (Entities): Représentent les objets persistants dans la base de données.
- Repositories: Interagissent avec la base de données.


## API REST

Le backend expose des endpoints pour interagir avec différentes entités. Voici quelques exemples d'endpoints :

### Centres

- GET /public/centres: Recherche des centres d'une ville donnée.
- POST /admin/centres: Crée un nouveau centre.
- PUT /admin/centres/{id}: Met à jour les informations d'un centre existant.
- DELETE /admin/centres/{id}: Supprime un centre.

### Administrateurs

- GET /admin/administrateurs: Récupère la liste des administrateurs.
- POST /admin/administrateurs: Crée un nouvel administrateur.
- PUT /admin/administrateurs/{id}: Met à jour les informations d'un administrateur.
- DELETE /admin/administrateurs/{id}: Supprime un administrateur.

### Médecins

- GET /admin/medecins: Récupère la liste des médecins.
- POST /admin/medecins: Crée un nouveau médecin.
- PUT /admin/medecins/{id}: Met à jour les informations d'un médecin.
- DELETE /admin/medecins/{id}: Supprime un médecin.


## Tests

Le backend est accompagné d'un ensemble de tests unitaires pour garantir la stabilité et la fiabilité des fonctionnalités. Les tests sont implémentés à l'aide de JUnit5 et Mockito. Ils couvrent divers aspects, notamment la logique métier des services, les contrôleurs, et les exceptions.

Pour exécuter les tests, vous pouvez utiliser les commandes suivantes :

````bash
    # Exécution de tous les tests
    gradle test
    
    # Exécution d'un test spécifique
    gradle test --tests com.example.package.NomDuTest
    
    # Exemple
    gradlew test --tests com.example.CentreDeVaccination.PatientRestControllerTest.testGetAllPatients
````

## Base de Données

### Configuration

Le backend est configuré pour utiliser PostgreSQL comme système de gestion de base de données. Assurez-vous que vous avez une instance PostgreSQL en cours d'exécution.

### Connection

Les paramètres de connexion à la base de données peuvent être configurés dans le fichier application.properties. Assurez-vous de spécifier correctement l'URL de la base de données, le nom d'utilisateur et le mot de passe.

````bash
    url: jdbc:postgresql://localhost:5432/nom_de_la_base_de_donnees
    username: nom_utilisateur
    password: mot_de_passe
````

### Création de la Base de Données

Au démarrage de l'application, la base de données est automatiquement créée (si elle n'existe pas déjà) grâce à la configuration Hibernate. Assurez-vous que l'utilisateur configuré a les droits nécessaires pour créer une base de données.


## Build et Exécution du Projet

Le backend est construit à l'aide de Gradle, un système de gestion de build. Voici comment vous pouvez construire et lancer le projet :

### Construction

```bash
    gradle clean build
```

### Exécution

```bash
    gradle run
```

L'application sera accessible à l'adresse http://localhost:8083 après son démarrage.


## Contact

En cas de problème ou de question, n'hésitez pas à me contacter à l'adresse
mail `bill-ruben.mbiawa-leuna1@etu.univ-lorraine.fr`.

---
