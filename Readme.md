# Centre de Vaccination

Le projet CentreDeVaccination est une application Java qui permet de gérer les vaccinations contre le COVID-19.
L'application permet de créer des rendez-vous de vaccination, de gérer les stocks de vaccins et de suivre l'état d'avancement de la vaccination.

Lien vers le front-end : https://github.com/BillLeuna/centre-de-vaccination-front.git

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
   gradle build
   ```

2. Lancez les tests :
   ```bash
   gradle test
   ```
2. Lancez l'application :
   ```bash
   gradle run
   ```

3. L'application sera accessible sur le port `8083`.


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



## Contact

En cas de problème ou de question, n'hésitez pas à me contacter à l'adresse
mail `bill-ruben.mbiawa-leuna1@etu.univ-lorraine.fr`.

---
