# Centre de Vaccination

Le projet CentreDeVaccination est une application Java qui permet de gérer les vaccinations contre le COVID-19.
L'application permet de créer des rendez-vous de vaccination, de gérer les stocks de vaccins et de suivre l'état d'avancement de la vaccination des patients.

Lien vers le front-end : https://github.com/BillLeuna/centre-de-vaccination-front.git

Les captures d'écran du fonctionnement sont disponibles dans le dossier `captures`

## Technologies Utilisées

1. Java 17: Langage de programmation principal.
2. Spring Boot: Cadre de développement pour la création d'applications Java.
3. Spring Data JPA: Couche d'abstraction pour l'accès aux données avec le modèle de programmation Java Persistence API (JPA).
4. Spring MVC: Module pour la création de services Web RESTful.
5. PostgreSQL: Système de gestion de base de données relationnelle.
6. JUnit5 et Mockito: Outils de test.

## Préréquis

Pour installer et exécuter le projet, vous devez disposer des éléments suivants :

1. Java 17
2. Un IDE Java (IntelliJ IDEA, Eclipse, etc.)
3. Git
4. pgAdmin 
5. Jenkins
6. Docker

## Installation

Pour installer le projet, vous pouvez suivre les étapes suivantes :

1. Clonez le projet depuis GitHub:
   ```bash
   git clone https://github.com/BillLeuna/centre-de-vaccination-back.git
   ```

2. Naviguez vers le répertoire du projet :
   ```bash
   cd centre-de-vaccination-back
   ```
   
3. Créez une base de données dans pgAdmin nommée `DataBaseTest`.


## Exécution

Pour exécuter le projet en local, vous pouvez suivre les étapes suivantes :

1. Installez les dépendances du projet :
    ```bash
   gradle clean build
   ```
   
2. Lancez l'application :
   ```bash
   gradle run
   ```

3. L'application sera accessible sur le port `8083`.

## Tests

Le backend est accompagné d'un ensemble de tests unitaires pour garantir la stabilité et la fiabilité des fonctionnalités. Les tests sont implémentés à l'aide de JUnit5 et Mockito. Ils couvrent divers aspects, notamment la logique métier des services, les contrôleurs, et les exceptions.

Pour exécuter les tests, vous pouvez utiliser les commandes suivantes :
```bash
    # Exécution de tous les tests
    gradle test
    
    # Exécution d'un test spécifique
    gradle test --tests com.example.package.NomDuTest
    
    # Exemple
    gradlew test --tests com.example.CentreDeVaccination.PatientRestControllerTest.testGetAllPatients
```
    
## Build depuis Jenkins

Le projet est également accompagné d'un pipeline Jenkins qui permet de lancer des builds automatiques. Pour configurer
le pipeline Jenkins, vous devez suivre les étapes suivantes :

1. **Configuration du Pipeline :**
    - Créez un pipeline Jenkins et nommez le `Gestion de centre de vaccination`
    - Dans la page suivante, dans la partie Pipeline, sélectionnez `Pipeline script from SCM`
    - Dans SCM, sélectionnez `Git`
    - Dans repository, mettez le lien vers le dépôt Github de ce projet: `https://github.com/BillLeuna/centre-de-vaccination-back.git`
    - Dans branch to build, mettez `*/release`
    - Sauvegardez cette configuration



2. **Exécution du Pipeline :**
    - Exécutez le Pipeline ci-dessus et appréciez les résultats


## Documentation

Le projet CentreDeVaccination est construit et déployé automatiquement sur Jenkins. Le pipeline Jenkins est défini dans le fichier Jenkinsfile.

Le pipeline Jenkins suit les étapes suivantes :

1. **Build du projet :** 

Le pipeline commence par construire le projet à l'aide de Gradle. Il utilise la tâche `clean build` pour installer les dépendances du projet, compiler le code source et générer l'artefact final.

2. **Vérification des conteneurs :**

Le pipeline vérifie ensuite si les conteneurs `centre-de-vaccination-api` et `centre-de-vaccination-database` sont en cours d'exécution. Si les conteneurs existent, ils sont arrêtés et supprimés.

3. **Lancement des conteneurs :**

Le pipeline lance ensuite les conteneurs `centre-de-vaccination-api` et `centre-de-vaccination-database` à l'aide de `docker compose up -d`.

Une fois le pipeline terminé, l'application est accessible sur le port `8090`.

## Structure du Projet

Le backend suit une architecture modulaire avec les composants suivants :

1. Contrôleurs (Controllers): Gèrent les requêtes HTTP et appellent les services appropriés.
2. Services: Contiennent la logique métier.
3. Entités (Entities): Représentent les objets persistants dans la base de données.
4. Repositories: Interagissent avec la base de données.

## API REST

Le backend expose des endpoints pour interagir avec différentes entités. Voici quelques exemples d'endpoints :

### Centres

1. GET /public/centres: Recherche des centres d'une ville donnée.
2. POST /admin/centres: Crée un nouveau centre.
3. PUT /admin/centres/{id}: Met à jour les informations d'un centre existant.
4. DELETE /admin/centres/{id}: Supprime un centre.

### Administrateurs
1. GET /admin/administrateurs: Récupère la liste des administrateurs.
2. POST /admin/administrateurs: Crée un nouvel administrateur.
3. PUT /admin/administrateurs/{id}: Met à jour les informations d'un administrateur.
4. DELETE /admin/administrateurs/{id}: Supprime un administrateur.

### Médecins
1. GET /admin/medecins: Récupère la liste des médecins.
2. POST /admin/medecins: Crée un nouveau médecin.
3. PUT /admin/medecins/{id}: Met à jour les informations d'un médecin.
4. DELETE /admin/medecins/{id}: Supprime un médecin.

## Contact

En cas de problème ou de question, n'hésitez pas à me contacter à l'adresse
mail `bill-ruben.mbiawa-leuna1@etu.univ-lorraine.fr`.

---
