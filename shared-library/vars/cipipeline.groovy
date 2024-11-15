def call() {
    // Declarative Pipeline - V2 Code.
    pipeline {
        agent { node { label 'workstation' } }

        options {
            ansiColor('xterm')
            buildDiscarder(logRotator(numToKeepStr: '3'))
        }

        stages {
            stage('CodeCompileAndBuilt') {
                steps {
                    script {
                        echo "Running Code Compile and Build stage"
                    }
                }
            }

            stage('UnitTest') {
                steps {
                    echo "Code Unit Testing"
                }
            }

            stage('CodeQuality') {
                steps {
                    echo "Check Code Quality"
                }
            }

            stage('Release') {
                steps {
                    echo "Do the Release"
                }
            }
        }

        post {
            always {
                script {
                    echo "Build finished"
                    cleanWs()
                }
            }
        }
    }

}