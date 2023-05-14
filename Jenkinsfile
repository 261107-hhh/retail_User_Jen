pipeline {
    agent any
	tools {
        // Install the Maven version configured as "M3" and add it to the path.
         maven 'mvn'
    }
    stages {
        stage('Build') {
            steps {
              bat 'mvn clean compile'
              //	bat 'mvn clean install -Dmaven.test.skip=true'
              //bat 'mvn clean install -Dmaven.test.skip=true'
              //bat "mvn -Dmaven.test.failure.ignore=true clean package"
                echo 'mvn clean build and compiled'
            }
        }
         stage('Test') {
            steps {
//                 bat './mvnw test'
                bat 'mvn test'
                echo 'mvn tested'
            }
        }
        stage('Deploy') {
            steps {
//                 bat './mvnw deploy'
                bat 'mvn deploy'
                echo 'mvn deployed'
            }
        }
       
    }
}
