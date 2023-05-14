pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
//                 bat './mvnw clean install'
                bat 'mvn clean compile'
                echo 'mvnw compiled'
            }
        }
        stage('Test') {
            steps {
//                 bat './mvnw test'
                bat 'mvn test'
                echo 'mvnw tested'
            }
        }
        stage('Deploy') {
            steps {
//                 bat './mvnw deploy'
                bat 'mvn deploy'
                echo 'mvnw deployed'
            }
        }
    }
}
