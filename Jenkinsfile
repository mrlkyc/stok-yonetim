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
                // Spring Boot'u arka planda başlat
                bat 'start "" cmd /c mvn spring-boot:run'

                // 20 saniye bekle (timeout yerine ping kullanılır)
                bat 'ping 127.0.0.1 -n 20 > nul'
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
