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
              echo 'mvn clean and compiled'
              
              //bat 'mvn clean install'
              //echo 'mvn clean and build'
              
              //bat 'mvn clean install -Dmaven.test.skip=true'
              
              //bat "mvn -Dmaven.test.failure.ignore=true clean package"
 			  //bat 'mvn package'
              //echo 'packed'  
            }
        }
         stage('Test') {
            steps {
                //bat './mvnw test'
                //bat 'mvn test'
                //bat 'mvn test'
                //echo 'mvn tested'
                bat 'mvn package'
                echo 'packed and tested'  
            }
        }
         stage('Deploy') {
            steps {
	            bat 'java -jar target/UserMicroservice-0.0.1-SNAPSHOT.jar'
                echo 'mvn deployed'
            }
        }
       
       
    }
}
