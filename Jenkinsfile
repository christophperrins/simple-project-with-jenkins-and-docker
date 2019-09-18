pipeline {
    agent any

    stages {
        stage('Clean') {
            steps {
                sh 'sudo docker-compose down'
            }
        }
        stage('Junit Tests') {
            steps {
                dir("server/") {
                    sh 'mvn test -Dtest=ControllerAndServiceSuite'
                }
            }
        }
        stage('Build') {
            steps {
                sh 'sudo docker-compose up'
            }
        }
        stage('Selenium Tests') {
            steps {
                sh 'mvn test -Dtest=SeleniumSuite'
            }
        }
    }
}
