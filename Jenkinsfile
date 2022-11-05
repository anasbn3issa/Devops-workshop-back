pipeline {
    agent any
   environment {
       dockerImage = ''
       registry = 'anasbn3issa/devops-fournisseur-image'
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "M2_HOME =${M2_HOME}"
                '''
            }
        }
        stage('Test') {
            steps {
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
        
        stage('Package') {
            steps {
                sh 'mvn -DskipTests clean package' 
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build registry
                }
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