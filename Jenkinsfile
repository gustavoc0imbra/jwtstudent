pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'Java 17'
    }

    stages {
        stage('Clone') {
            steps {
                git 'https://github.com/gustavoc0imbra/jwtstudent'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests',
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executado com sucesso'
        }
        failure {
            echo ' erro no Pipeline'
        }
    }
}
