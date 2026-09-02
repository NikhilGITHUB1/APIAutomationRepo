pipeline {

    agent any

    tools {
        maven 'maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                        url: 'https://github.com/NikhilGITHUB1/APIAutomationRepo.git'
            }
        }

        stage('Run API Automation Tests') {
            steps {
                bat 'mvn clean test -Denv=qa'
            }
        }

    }

    post {

        success {
            echo 'API Automation Pipeline PASSED'
        }

        failure {
            echo 'API Automation Pipeline FAILED'
        }

        always {
            echo 'Pipeline execution completed'
        }
    }
}