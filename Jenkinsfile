// DSL Language
pipeline {
    // agent any

    // Mention the nodes.
    agent { node { label 'workstation' } }

    // Poliscm trigger to fetch the changes every one minute.
    // triggers { pollSCM('*/3 * * * *') }

    // Ansi Colour options
    options {
        ansiColor('xterm')
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }

    // Access Variables.
    environment {
    TEST_URL = "google.com"
    SSH = credentials("centos-ssh")
    }

    // Access the secret that is stored in Jenkins.
    // environment { SSH = credentials("centos-ssh")}

    stages {
        stage('Compile') {
            steps {
                echo TEST_URL
                echo SSH
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