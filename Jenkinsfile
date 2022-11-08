pipeline {
    agent any

    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
    }
    environment {
        DOCKER_REGISTRY_CREDENTIALS = 'dockerhub'
        DOCKER_REGISTRY = 'https://index.docker.io/v2/'
        DOCKER_IMAGE = 'parsath/reglement'
	}

    stages {
        stage('Testing with maven') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Build') {
            steps {
                echo 'Building... ';
                    git branch: 'main',
                    url: 'https://github.com/Parsath/dev-ops-initiation.git'

                sh "mvn -Dmaven.test.failure.ignore=true clean package"
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
        stage('Checkout GIT') {
            steps {
                echo 'Pulling... ';
                    git branch: 'main',
                    url: 'https://github.com/Parsath/dev-ops-initiation.git'
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
