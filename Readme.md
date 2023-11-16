# Centre de Vaccination

Ce projet vise à créer une application de réservation dans un centre de vaccination Covid. L'application offre un accès public permettant la recherche des centres par ville et l'inscription à un centre. Elle offre également un accès administrateur permettant la gestion complète des centres, des administrateurs, des médecins, et des réservations.

Lien vers le front-end: https://github.com/BillLeuna/centre-de-vaccination-front.git

## Enrôlement dans le Projet

1. Clonez le projet depuis GitHub:
   ```bash
   git clone https://github.com/BillLeuna/centre-de-vaccination-back.git
   ```

2. Naviguez vers le répertoire du projet:
   ```bash
   cd centre-de-vaccination-back
   ```

## Build du Projet

### Build Gradle Local

1. Initialisez le projet avec Gradle:
   ```bash
   gradle init
   ```

2. Gérez les dépendances dans le fichier `build.gradle`.

3. Gérez les plugins dans le fichier `build.gradle`.

4. Buildez le projet localement :
   ```bash
   ./gradlew build
   ```

5. Lancez l'application localement :
   ```bash
   ./gradlew run
   ```

### Build Gradle avec Jenkins


1. **Prérequis:**
   - Assurez-vous d'avoir un compte GitHub ([https://github.com](https://github.com)) et un compte DockerHub ([https://hub.docker.com](https://hub.docker.com)).
   - Docker doit être installé sur votre machine.
   - Git doit être installé sur votre machine.
   - Jenkins doit être installé et avoir les droits nécessaires pour accéder au dépôt GitHub


2. **Pipeline Jenkins:**
   - Créez un nouveau pipeline Jenkins en utilisant le script ci-dessous comme exemple.
     ```groovy
     pipeline {
         agent any

         stages {
             stage('Build Backend') {
                 steps {
                     script {
                         // Changez le répertoire de travail avant d'exécuter Gradle
                         dir("C:\\Users\\Utilisateur\\Documents\\centre-de-vaccination-back") {
                             bat '"C:\\Program Files\\gradle-8.3\\bin\\gradle.bat" clean build -Dserver.port=8082'
                         }
                     }
                 }
             }

             stage('Build Docker Image') {
                 steps {
                     // Changez le chemin en fonction de la localisation des différents fichiers et documents dans votre ordinateur 
                     bat 'docker build -t image_test_2:latest -f "C:\\Users\\Utilisateur\\Documents\\centre-de-vaccination-back\\Dockerfile" C:\\Users\\Utilisateur\\Documents\\centre-de-vaccination-back'
                     bat 'docker run -p 8080:8080 image_test_2:latest'
                 }
             }
         }
     }
     ```


3. **Exécution du Pipeline:**
   - Le pipeline ci-dessus effectue le build du backend et crée une image Docker.
   - Assurez-vous que le chemin vers Gradle et le fichier Dockerfile est correct.

4. **Points d'Attention:**
   - Le conteneur ne doit pas s'exécuter en tant que root.
   - Les prérequis de l'environnement pour builder un projet Gradle doivent être satisfaits.
   - Le type de build utilisé est Gradle.
   - L'artefact fabriqué (image de conteneur) est stocké localement.
   
## Spécifications

### Accès Public

- Recherche des centres par ville (R)
- Inscription à un centre (mail, téléphone, nom, prénom, date) (C)

### Accès Administration

#### Pour le Super Admin

- Gestion des centres (CRUD)
- Gestion des administrateurs des centres (CRUD)

#### Pour l’Administrateur

- Gestion des médecins de son centre de vaccination (CRU)
- Gestion des réservations de son centre (RD)

#### Pour le Médecin

- Recherche d’une personne par son nom (à l’arrivée d’une personne dans le centre) (R)
- Valider la vaccination d’une personne (au départ de la personne) (U)

### Travaux réalisés lors du TD1

- Entity Bean
- Mapping objet/relationnel
- Couche repository
- Couche RESTController
  - Une partie /public/*
  - Une partie /admin/*
- Couche service
  - RESTController => Service => Repository

### Travaux de Dév. Front - Angular - Loïc Gangloff

#### Application public de réservation de créneau

- Recherche des centres d’une ville choisie
- Inscription à un centre (mail, téléphone, nom, prénom, date)
- Penser en termes de composants
- Gérer la communication entre composants
- Mettre en place un type de navigation
- Encapsuler la logique métier dans des services
- Implémenter les appels à l'API HTTP

#### Application back office

- Gestion des centres, administrateurs et super admins pour le super admin
- Gestion des médecins et des réservations pour l'administrateur
- Recherche d’une personne et validation de la vaccination pour le médecin

## Contact

En cas de problème ou de question, n'hésitez pas à me contacter à l'adresse mail `bill-ruben.mbiawa-leuna1@etu.univ-lorraine.fr`.

---
