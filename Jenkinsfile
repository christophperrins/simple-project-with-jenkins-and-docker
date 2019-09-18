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
                cd server/
                sh 'mvn test -Dtest=ControllerAndServiceSuite'
                cd ..
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
