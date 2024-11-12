pipeline {
    agent { node { label 'workstation' } }

    options {
        ansiColor('xterm')
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }

    stages {
        stage('Compile') {
            steps {
                script {
                   sh 'pwd'
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

    post {
        always {
            script {
                echo "Build finished"
                cleanWs() // Optionally clean up the workspace after the build
            }
        }
    }

}
