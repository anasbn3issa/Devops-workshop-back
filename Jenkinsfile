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
                withSonarQubeEnv('sonarqube') {
                    sh "mvn sonar:sonar"
                }
            }
        }
        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
        stage('Package') {
            steps {
                sh 'mvn -DskipTests clean package' 
            }
        }
        stage('Build Docker') {

			steps {
				sh 'docker build -t wajdisd/springbootapp:1.0 .'
			}
		}
        stage('Login') {

			steps {

                sh ' docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW'                		
	            echo 'Login Completed'  
			}
		}

		stage('Push') {

			steps {
				sh 'docker push wajdisd/springbootapp:1.0'
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