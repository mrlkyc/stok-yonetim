pipeline {
    agent any

    stages {

        stage('Git Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }

    post {
        success {
            echo 'BUILD VE TESTLER BASARILI'
        }
        failure {
            echo 'BUILD VEYA TEST HATASI VAR'
        }
    }
}
