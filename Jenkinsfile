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
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
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
