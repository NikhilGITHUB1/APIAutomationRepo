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
                // OLD: Running Maven directly from Jenkins
                // bat 'mvn clean test -Denv=qa'

                // NEW: Running tests inside Docker container
                bat 'docker run --rm --name apitesting%BUILD_NUMBER% nikhil990/apiautomation:latest mvn clean test -Denv=qa'
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