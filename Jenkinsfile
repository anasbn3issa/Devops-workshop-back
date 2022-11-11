pipeline {
    agent any

    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }
    environment {
        NEXUS_CREDENTIALS = credentials('nexus')
        DOCKER_REGISTRY_CREDENTIALS = 'dockerhub'
        DOCKER_REGISTRY = 'https://index.docker.io/v2/'
        DOCKER_IMAGE = 'parsath/reglement'
        SONAR_HOST_URL = 'http://172.10.0.140:9000'
        SONAR_LOGIN = 'admin'
        SONAR_PASSWORD = 'vagrant' 
	}

    stages {
        stage('Pulling from GIT') {
            steps {
                echo 'Pulling... ';
                    git branch: 'main',
                    url: 'https://github.com/Parsath/dev-ops-initiation.git'
            }
        }
        stage('Testing with maven') { 
            steps {
                sh 'mvn test'
            }
        }
        stage('Build') {
            steps { 
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('Launch Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }
        stage('SonarQube') {
            steps {
                echo 'SonarQube... ';
                sh "mvn sonar:sonar -Dsonar.host.url=$SONAR_HOST_URL -Dsonar.login=$SONAR_LOGIN -Dsonar.password=$SONAR_PASSWORD"
            }
        }
        stage('Deploy to Nexus') {
              steps {
                script {
		        nexusArtifactUploader artifacts: [[artifactId: 'tpAchatProject', classifier: '', file: 'target/tpAchatProject-1.0.jar', type: 'jar']], credentialsId: 'nexus', groupId: 'com.esprit.examen', nexusUrl: 'localhost:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '1.0.0-SNAPSHOT'
			    }
            }
        }
        stage('Build and Push Docker Image') {
            steps {
                script {
                    docker.withRegistry( '', DOCKER_REGISTRY_CREDENTIALS ) {
                        def customImage = docker.build("${DOCKER_IMAGE}:latest")
                        customImage.push() 
                    }
                }
            }
        }

    }
    post {
        always {
            junit '**/target/surefire-reports/TEST-*.xml'
            archiveArtifacts 'target/*.jar'
        }
    }
}
