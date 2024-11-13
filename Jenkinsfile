// DSL Pipeline

// pipeline {
//     agent { node { label 'workstation' } }
//
//     options {
//         ansiColor('xterm')
//         buildDiscarder(logRotator(numToKeepStr: '3'))
//     }
//
//     stages {
//         stage('Compile') {
//             steps {
//                 script {
//                     // Add valid shell commands here
//                     sh 'echo "Running compile stage"'
//                     sh 'pwd'
//                     sh 'ls -l'
//                 }
//             }
//         }
//
//         stage('Hello') {
//             steps {
//                 echo "Hello World"
//             }
//         }
//
//         stage('Test') {
//             steps {
//                 echo "Test for pollSCM"
//             }
//         }
//     }
//
//     post {
//         always {
//             script {
//                 echo "Build finished"
//                 cleanWs()
//             }
//         }
//     }
// }


//  Scripted Pipeline

// Define Variable

node('workstation'){
    // Access the variable using print.
    def x = 10

    // Access the variable using echo.
    env.y = 20

    stage('Access_Variable'){
        print x
        sh 'echo y is $(y)'
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