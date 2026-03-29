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
                bat 'docker-compose down --remove-orphans || exit 0'
				bat 'docker rm -f test-runner selenium-hub || exit 0'
				bat 'docker-compose up -d --force-recreate'
                /*bat 'timeout /t 20'*/
                bat 'ping 127.0.0.1 -n 20 > nul'
                bat 'mvn clean test'
                bat 'docker-compose down'
            }
        }
    }
}