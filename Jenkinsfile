pipeline {
    agent any

    tools {
        maven 'M2_HOME'
        jdk 'JAVA_HOME'
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

                // Run Maven on a Unix agent.µ
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
                // sh 'mvn -DskipTests clean package' 
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
        // If Maven was able to run the tests, even if some of the test
        // failed, record the test results and archive the jar file.
        always {
            // junit '**/reports/junit/*.xml'
            junit '**/target/surefire-reports/TEST-*.xml'
            // archiveArtifacts 'target/*.jar'
        }
    }
}
