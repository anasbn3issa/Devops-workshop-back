pipeline {
 agent any
tools {
        maven 'Maven3'
      }
environment {
        EMAIL_TO = 'cyrine.louati@esprit.tn'
      }
 stages {

	stage('Checkout GIT'){
	
	steps {
		echo 'Pulling from GIT...';
    		sh """ mvn --version """;
		sh """ mvn -DskipTests clean install """;
		}
	post 	{
 		failure {
  		emailext body: 'Build FAILED', 
                    to: "${EMAIL_TO}", 
                    subject: 'Build failed in Jenkins: $PROJECT_NAME'
        
 			} 
		}
	
	}
	
	stage('Unit tests'){
	
	steps {
		echo 'RUN AS : Maven test...';
		sh """ mvn test """;
		}
	
	}

	stage('MVN SONARQUBE'){
	
	steps {
		echo 'Analyzing quality of code...';

		}
	
	}

	stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "mvn sonar:sonar"
                }
            }
        }

        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }


	}
}