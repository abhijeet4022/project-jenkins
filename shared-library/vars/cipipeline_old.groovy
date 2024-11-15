def call() {
    // Declarative Pipeline - V2 Code.
    pipeline {
        agent { node { label 'workstation' } }

        options {
            ansiColor('xterm')
            buildDiscarder(logRotator(numToKeepStr: '3'))
        }


        stages {
//          Do the Code Compile And Built in main and sub branch but not in tag.
            stage('CodeCompileAndBuilt') {
                when {
                    allOf {
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

//          Do the Code UnitTest in main and sub branch but not in tag.
            stage('UnitTest') {
                when {
                    allOf {
                        expression { env.BRANCH_NAME != null }
                        expression { env.TAG_NAME == null }
                    }
                }
                steps {
                    echo "Code Unit Testing"
                }
            }

//          Do the Code security check in main and sub branch but not in tag.
            stage('CodeQuality') {
                when {
                    allOf {
                        expression { env.BRANCH_NAME != null }
                        expression { env.TAG_NAME == null }
                    }
                }
                steps {
                    echo "Check Code Quality"
                }
            }

//          Do the Code security check only in main branch.
            stage('CodeSecurity') {
                when {
                    expression { env.BRANCH_NAME == "main" }
                }
                steps {
                    echo "Check Code Security"
                }
            }

//          Release the Application only if tag is given or found.
            stage('Release') {
                when {
                    expression { env.TAG_NAME ==~ ".*" }
                }
                steps {
                    echo "Do the Release"
                }
            }
        }

//      Clea up the Workspace
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