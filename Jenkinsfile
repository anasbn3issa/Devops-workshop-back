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
		echo 'Creating PACKAGE...';
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
	
	stage('Unit TESTS'){
	
	steps {
		echo 'MVN TESTS';
		sh """ mvn test """;
		}
	
	}

	stage('MVN SONARQUBE'){
	
	steps {
		echo 'ANALYZING QUALITY OF CODE...';

		}
	
	}
	/*
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
	

	stage('Nexus') {
            steps {
                script {
		nexusArtifactUploader artifacts: [[artifactId: 'tpAchatProject', classifier: '', file: 'target/tpAchatProject-1.0.jar', type: 'jar']], credentialsId: 'Nexus', groupId: 'com.esprit.examen', nexusUrl: '192.168.1.17:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '1.0.0-SNAPSHOT'
			}
            }
            
        }
	*/

	stage('DOCKER : Step 1'){
	
	steps {
		echo 'Step 1 : Build image via dockerFile'
		sh 'docker build -t cyrinelo/tpachatproject:1.0 .'
		echo 'Checking image'
		sh 'docker images'
		}
	
	}

	stage('DOCKER : Step 2'){
	
	steps {
		echo 'Step 2 : Login'
		sh 'docker login -u cyrinelo -p 181JFT0975'
		echo 'Successfully logged in.'
		}
	
	}
	
	stage('DOCKER : Step 3'){
	
	steps {
		echo 'Step 3 : Pushing image to Hub
		sh 'docker push cyrinelo/tpachatproject:1.0'
		echo 'Push done.'
		}
	
	}
	




	}
}