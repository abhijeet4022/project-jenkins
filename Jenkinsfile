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


// Jenkins Scripted Pipeline.

node('workstation') {

// 1. Define Variables
    def x = 10           // Dynamically typed variable using 'def'
    int z = 30           // Explicitly typed integer variable
    env.y = '20'         // Environment variable (must be a string)

// 2. Access Variables in Stage
    stage('Access_Variable') {
        // Using print to display the variable 'x'
        print x

        // Accessing environment variable 'y' using double quotes (Groovy interpolation)
        sh "echo y is ${env.y}"

        // This also works same but isn't recommended
        sh 'echo y - ${y}'

        // Display the value of integer variable 'z' using echo
        echo "z is ${z}"       // Prints 'z is 30'

        // Using print to display the value of 'z' (less preferred than echo in Jenkins)
        print z                // Prints '30'
    }

// 3. Post Build Actions
    post {
        always {
            script {
                echo "Build finished" // Log message indicating the build completion
                cleanWs()             // Cleans the workspace after the build
            }
        }
    }
}
