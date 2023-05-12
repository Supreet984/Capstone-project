pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        // Build the application package using Maven
        sh './mvnw package'
        
        // Build and push Docker image to Google Cloud Registry
        sh 'docker build -t gcr.io/triple-bonito-384414/github.com/supreet984/capstone-project .'
        sh 'docker push gcr.io/triple-bonito-384414/github.com/supreet984/capstone-project'
      }
    }
    stage('SonarQube Analysis') {
      steps {
        // Run SonarQube analysis on the code
        withSonarQubeEnv('SonarQube') {
          sh './mvnw sonar:sonar'
        }
      }
    }
    stage('Deploy') {
      environment {
        // Set the Kubernetes configuration using credentials stored in Jenkins
        KUBECONFIG = credentials('kubeconfig')
      }
      steps {
        // Apply the Kubernetes deployment and service YAML files
        sh 'kubectl apply -f deployment.yml'
        sh 'kubectl apply -f service.yml'
        
        // Install SSL certificate on the server
        sh 'kubectl create secret tls tls-secret --cert=cert.pem --key=key.pem'
      }
    }
  }
}
