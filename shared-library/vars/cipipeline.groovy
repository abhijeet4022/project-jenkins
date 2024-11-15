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
                when {
                    alloff {
                        expression { env.BRANCH_NAME != null }
                        expression { env.TAG_NAME == null }
                    }
                }
                steps {
                    script {
                        echo "Running Code Compile and Build stage"
                    }
                }
            }

            stage('UnitTest') {
                when {
                    alloff {
                        expression { env.BRANCH_NAME != null }
                        expression { env.TAG_NAME == null }
                    }
                }
                steps {
                    echo "Code Unit Testing"
                }
            }

            stage('CodeQuality') {
                when {
                    alloff {
                        expression { env.BRANCH_NAME != null }
                        expression { env.TAG_NAME == null }
                    }
                }
                steps {
                    echo "Check Code Quality"
                }
            }

            stage('CodeSecurity') {
                when {
                    alloff {
                        expression { env.BRANCH_NAME == main }
                    }
                }
                steps {
                    echo "Check Code Security"
                }
            }

            stage('Release') {
                when {
                    expression { env.TAG_NAME ==~ ".*" }
                }
                steps {
                    echo "Do the Release"
                }
            }
        }

        post {
            always {
                script {
                    echo "Cleaning Workspace"
                    cleanWs()
                }
            }
        }
    }

}