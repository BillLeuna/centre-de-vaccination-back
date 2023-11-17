# Centre de Vaccination

Le projet CentreDeVaccination est une application Java qui permet de gérer les vaccinations contre le COVID-19.
L'application permet de créer des rendez-vous de vaccination, de gérer les stocks de vaccins et de suivre l'état d'avancement de la vaccination des patients.

Les captures d'écran du fonctionnement sont disponibles dans le dossier `captures`

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

1. Lancer un build en local (cette commande réalise également les tests) :
    ```bash
   gradle clean build
   ```
   
2. Démarrer l'application :
   ```bash
   gradle run
   ```

3. L'application sera accessible sur le port `8083`.


Arrêtez l'application avant de passer à l'étape suivante.


## Build depuis Jenkins

Le projet est également accompagné d'un pipeline Jenkins qui permet de lancer des builds automatiques. Pour configurer
le pipeline Jenkins, vous devez suivre les étapes suivantes :

1. **Configuration du Pipeline :**
    - Créez un pipeline Jenkins et nommez le `Gestion de centre de vaccination`
    - Dans la page suivante, dans la partie Pipeline, sélectionnez `Pipeline script from SCM`
    - Dans SCM, sélectionnez `Git`
    - Dans repository, mettez le lien vers le dépôt Github de ce projet: `https://github.com/BillLeuna/centre-de-vaccination-back.git`
    - Dans branch to build, mettez `*/release-mise-en-production`
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



## Contact

En cas de problème ou de question, n'hésitez pas à me contacter à l'adresse
mail `bill-ruben.mbiawa-leuna1@etu.univ-lorraine.fr`.

---
