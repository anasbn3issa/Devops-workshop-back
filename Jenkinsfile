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

		// stage('SonarQube analysis') {
        //     steps {
        //         sh ''' mvn sonar:sonar \
        //             -Dsonar.projectKey=devops-fournisseur \
        //             -Dsonar.host.url=http://localhost:9000 \
        //             -Dsonar.login=76e19b86c532f4803ce6f271ee4f131f6794f81e '''
        //     }
        // }
        stage('MVN PACKAGE') {
            steps {
                sh 'mvn -DskipTests clean package' 
            }
        }

        stage('Deploy to Nexus') {
              steps {
                // script {
		        // nexusArtifactUploader artifacts: [[artifactId: 'tpAchatProject', classifier: '', file: 'target/tpAchatProject-1.0.jar', type: 'jar']], credentialsId: 'nexus', groupId: 'com.esprit.examen', nexusUrl: 'localhost:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots',version: '1.0.1-SNAPSHOT'
                // }

                // nexus key : 95075e3d-e40f-3aed-9af0-86b226cc4fa0
                sh 'echo $NEXUS_CREDENTIALS_PSW | mvn deploy:deploy-file -DgroupId=com.esprit.examen -DartifactId=tpAchatProject -Dversion=1.0.1-SNAPSHOT -Dpackaging=jar -Dfile=target/tpAchatProject-1.0.jar -Durl=http://localhost:8081/repository/maven-snapshots/ -DnexusUrl=http://localhost:8081 -DnexusVersion=nexus3 -Drepository=maven-snapshots -DnexusUsername=admin -DnexusPassword=181JMT3048'
                //sh 'mvn deploy -DskipTests -DaltDeploymentRepository=nexus::default::http://localhost:8081/repository/maven-snapshots/ -DnexusUrl=http://localhost:8081 -DnexusVersion=nexus3 -Drepository=maven-snapshots -DnexusUsername=admin -DnexusPassword=181JMT3048'
            }
            
        }

        
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t anasbn3issa/fournisseur .'
            }
        } 

        stage('Push Docker Image') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                sh 'docker push anasbn3issa/fournisseur'
            }
        }

        
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