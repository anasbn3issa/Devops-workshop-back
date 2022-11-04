pipeline {
    agent any
    tools {
        maven 'maven3'
        jdk 'JDK11'
    }
    environment {
		DOCKERHUB_CREDENTIALS=credentials('Docker')
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
                echo 'mvn package'
                sh """ mvn -DskipTests package """;
                echo 'tik tak 3'
            }
        }

        stage('Build') {
            steps {
                echo 'docker build'
                sh """ docker build -t anasbn3issa/devops-fournisseur . """;
                echo 'tik tak 4'
            }
        }
        stage('Login Dockerhub') {
            steps {
                echo 'docker login'
                sh """ docker login -u anasbn3issa -p $DOCKERHUB_CREDENTIALS_PSW """;
                echo 'tik tak 5'
            }
        }

        stage('Push') {
            steps {
                echo 'docker push'
                sh """ docker push anasbn3issa/devops-fournisseur """;
                echo 'tik tak 6'
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