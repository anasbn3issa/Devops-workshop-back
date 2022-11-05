pipeline {
    agent any
    tools {
        maven 'maven3'
        jdk 'JDK11'
    }
    environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub1')
	}
    stages {
        stage('test mvn') {
            steps {
                echo 'mvn --version'
                sh """ mvn clean install """;
                sh """ mvn clean test """;
                echo 'tik tak 1'
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                echo 'mvn sonar:sonar'
                sh """ mvn sonar:sonar \
                    -Dsonar.projectKey=devops-fournisseur \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.login=8f56f3043734867c864e3800f4cdcc189dfacc8b
                        """;
                echo 'tik tak 2'
            }
        }
        
        stage('Package') {
            steps {
                echo 'Package'
                sh """ mvn clean package """;
                echo 'tik tak 3'
            }
        }
        stage('Build Docker') {
            steps {
                echo 'Build Docker'
                sh """ docker build -t anasbn3issa/devops-fournisseur . """;
                echo 'tik tak 4'
            }
        }
        stage('Push Docker') {
            steps {
                echo 'Push Docker'
                withDockerRegistry([credentialsId: DOCKERHUB_CREDENTIALS, url: ""]) {
                    sh """ docker push anasbn3issa/devops-fournisseur """;
                }
                echo 'tik tak 5'
            }
        }
        
    }
    post {
        always {  
             echo 'This will always run'  
         }  
        failure {  
             mail bcc: '', body: "<b>BUILD FAILED</b>", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html',  subject: "BUILD FAILED", to: "mohamedanas.benaissa@esprit.tn";  
         }  
    }
}