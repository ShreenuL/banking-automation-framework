pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/ShreenuL/banking-automation-framework.git'
            }
        }

        stage('Run Tests with Docker') {
            steps {
                bat 'docker-compose down'
                bat 'docker-compose up -d'
                bat 'timeout /t 20'
                bat 'mvn clean test'
                bat 'docker-compose down'
            }
        }
    }
}