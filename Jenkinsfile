pipeline {
 agent any
tools {
        maven 'Maven3'
      }
environment {
        EMAIL_TO = 'cyrine.louati@esprit.tn'
	DOCKERLOGIN=credentials('DockerLogin')

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
	
	stage('TESTS : Unit tests'){
	
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
	stage('SONAR : Step 1') {
            steps {
		echo 'SonarQube analysis...';
                withSonarQubeEnv('SonarQube') {
                    sh "mvn sonar:sonar"
                }
            }
        }

        stage("SONAR : Step 2") {
            steps {
		echo 'Quality Gate...';
                waitForQualityGate abortPipeline: true
            }
        }
	*/

	stage('NEXUS') {
            steps {
                script {
		nexusArtifactUploader artifacts: [[artifactId: 'tpAchatProject', classifier: '', file: 'target/tpAchatProject-1.0.jar', type: 'jar']], credentialsId: 'Nexus', groupId: 'com.esprit.examen', nexusUrl: '192.168.1.17:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '1.0.0-SNAPSHOT'
			}
            }
            
        }
	

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
		sh 'docker login -u $DOCKERLOGIN_USR -p $DOCKERLOGIN_PSW'
		echo 'Successfully logged in.'
		}
	
	}
	/*
	stage('DOCKER : Step 3'){
	
	steps {
		echo 'Step 3 : Pushing image to Hub'
		sh 'docker push cyrinelo/tpachatproject:1.0'
		echo 'Push done'
		}
	
	}
	*/
	




	}
}