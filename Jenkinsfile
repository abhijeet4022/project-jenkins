pipeline {
    agent { node { label 'workstation' } }

    options {
        ansiColor('xterm')
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }

    environment {
        TEST_URL = "google.com"
        SSH = credentials("366954ce-8f22-4117-b10d-0717e4aefcb9")
    }

    stages {
        stage('Compile') {
            steps {
                script {
                    echo "Test URL: ${TEST_URL}"
                    echo "SSH Credentials ID: ${SSH}"
                }
            }
        }

        stage('Hello') {
            steps {
                echo "Hello World"
            }
        }

        stage('Test') {
            steps {
                echo "Test for pollSCM"
            }
        }
    }
}
