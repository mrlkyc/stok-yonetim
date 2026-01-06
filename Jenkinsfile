pipeline {
    agent any

    tools {
        maven 'maven-3'
        jdk 'jdk-21'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Start Application') {
            steps {
                // Spring Boot uygulamasını arka planda başlat
                bat 'start /B mvn spring-boot:run'
                // Uygulamanın ayağa kalkması için bekle
                bat 'timeout /T 20'
            }
        }

        stage('UI Test - Ana Sayfa') {
            steps {
                bat 'mvn test -Dtest=PageOpenTest#anaSayfaAcilmali'
            }
        }

        stage('UI Test - Ürün Ekleme') {
            steps {
                bat 'mvn test -Dtest=ProductSeleniumTest#urunEklemeCalisiyorMu'
            }
        }

        stage('UI Test - Ürün Listeleme') {
            steps {
                bat 'mvn test -Dtest=ProductSeleniumTest#urunListelemeCalisiyorMu'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
