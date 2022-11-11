pipeline {
    agent any
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-token')
        NEXUS_CREDENTIALS = credentials('nexus')
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.177.24:8081"
        NEXUS_CREDENTIAL_ID = "nexus"
        NEXUS_REPOSITORY= "https://github.com/anasbn3issa/Devops-workshop-back"

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
                script {
					nexusArtifactUploader artifacts: [[artifactId: 'tpAchatProject',
                     classifier: '', file: 'target/tpAchatProject-1.0.jar', type: 'jar']],
                      credentialsId: 'nexus', 
                      groupId: 'com.esprit.examen', 
                      nexusUrl: '192.168.177.24:8081',
                       nexusVersion: 'nexus3', 
                       protocol: 'http', 
                       repository: 'maven-snapshots',
                        version: '1.0.6-SNAPSHOT'
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
        // } //
       

        
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