pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'gradle build'
            }
        }

        stage('Test') {
            steps {
                sh 'gradle test'
            }
        }

        stage('Create Image') {
            steps {
                sh 'docker build -t my-api-image .'
            }
        }
    }
}