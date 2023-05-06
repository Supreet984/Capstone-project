pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './mvnw package'
        sh 'docker build -t gcr.io/triple-bonito-384414/github.com/supreet984/capstone-project .'
        sh 'docker push gcr.io/triple-bonito-384414/github.com/supreet984/capstone-project'
      }
    }
    stage('Deploy') {
      environment {
        KUBECONFIG = credentials('kubeconfig')
      }
      steps {
        sh 'kubectl apply -f deployment.yml'
        sh 'kubectl apply -f service.yml'
      }
    }
  }
}
