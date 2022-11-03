pipeline {
    agent any
    stages {
        stage('test mvn') {
            steps {
                echo 'mvn --version'
                sh """ mvn clean install """;
                sh """ mvn clean test """;
                echo 'tik tak 1'
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                echo 'mvn sonar:sonar'
                sh """ mvn sonar:sonar \
                        -Dsonar.projectKey=devops-fournisseur \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.login=8f64f36bcdfe572b323bb621432d73fd70360e66
                        """;
                echo 'tik tak 2'
            }
        }
        
    }
    post {
        always {  
             echo 'This will always run'  
         }  
        failure {  
             mail bcc: '', body: "<b>BUILD FAILED</b>", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html',  subject: "BUILD FAILED", to: "mohamedanas.benaissa@esprit.tn";  
         }  
    }
}
