pipeline {
    agent any

    environment {
        GRADLE_HOME = tool 'GRADLE_ENV'
        PATH = "${GRADLE_HOME}/bin:${PATH}"
        registry = ""
    }

    stages {
        stage('Build project') {
            steps {
                script {
                    // Utilisation de 'bat' pour exécuter des commandes spécifiques à Windows
                    bat "gradle clean build"
                }
            }
        }

        stage('Check containers') {
            steps {
                script {
                    // Liste des conteneurs Docker en cours d'exécution
                    def containers = bat(returnStdout: true, script: 'docker ps -a').split('\n')

                    // Vérifie si les conteneurs `centre-de-vaccination-api` et `centre-de-vaccination-database` sont présents dans la liste
                    def apiContainerExists = containers.find { it.contains('centre-de-vaccination-api') }
                    def databaseContainerExists = containers.find { it.contains('centre-de-vaccination-database') }

                    // Si les conteneurs existent, les arrête et les supprime
                    if (apiContainerExists) {
                        bat 'docker stop centre-de-vaccination-api'
                        bat 'docker rm centre-de-vaccination-api'
                    }
                    if (databaseContainerExists) {
                        bat 'docker stop centre-de-vaccination-database'
                        bat 'docker rm centre-de-vaccination-database'
                    }
                }
            }
        }

        stage('Start containers') {
            steps {
                script {
                    // Utilisation de 'bat' pour exécuter des commandes spécifiques à Windows
                    bat 'docker-compose up -d'
                }
            }
        }
    }
}