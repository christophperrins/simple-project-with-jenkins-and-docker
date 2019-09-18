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
                sh 'cd server/'
                sh 'mvn test -Dtest=ControllerAndServiceSuite'
                sh 'cd ..'
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
