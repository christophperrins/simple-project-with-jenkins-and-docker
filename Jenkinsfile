pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                dir("server/"){
                    sh 'mvn install -DskipTests'
                }
            }
        }
        stage('Junit Tests') {
            steps {
                dir("server/") {
                    sh 'mvn test -Dtest=ControllerAndServiceSuite'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'sudo docker-compose up -d'
            }
        }
        stage('Selenium Tests') {
            steps {
                dir("server/") {
                    sh 'mvn test -Dtest=SeleniumSuite'
                }
            }
        }
    }
}
