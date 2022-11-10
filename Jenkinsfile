pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-token')
        NEXUS_CREDENTIALS = credentials('nexus')
    }

    stages {
        stage('MVN TEST') {
            steps {
                echo 'mvn -v'
                sh 'mvn -v'
                sh 'mvn test'
            }
        }

		stage('SonarQube analysis') {
            steps {
                sh ''' mvn sonar:sonar \
                    -Dsonar.projectKey=devops-fournisseur \
                    -Dsonar.host.url=http://localhost:9000 \
                    -Dsonar.login=76e19b86c532f4803ce6f271ee4f131f6794f81e '''
            }
        }
        stage('MVN PACKAGE') {
            steps {
                sh 'mvn -DskipTests clean package' 
            }
        }

        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh 'mvn clean package deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.0 -DgeneratePom=true -Dpackaging=jar -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-snapshots -Dfile=target/tpAchatProject-1.0.jar -Dusername=$USERNAME -Dpassword=$PASSWORD'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t anasbn3issa/fournisseur .'
            }
        } 

        // stage('Push Docker Image') {
        //     steps {
        //         sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
        //         sh 'docker push anasbn3issa/fournisseur'
        //     }
        // }

        
    }
    post {
    failure {
            emailext body: '${DEFAULT_CONTENT}', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider']], subject: '${DEFAULT_SUBJECT}',
            to: '${DEFAULT_RECIPIENTS}'
    }
    always {
			sh 'docker logout'
	}
  }
}