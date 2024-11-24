def call() {
    pipeline {
        // The agent directive defines where the pipeline or stage will run. "any" means it can run on any available agent.
        agent any

        options {
            // Enables ANSI color output to make console logs easier to read.
            ansiColor('xterm')

            // Limits the number of build records and artifacts to retain, reducing storage usage.
            buildDiscarder(logRotator(
                    numToKeepStr: '4',   // Keep only the last 4 builds
                    artifactNumToKeepStr: '4' // Keep artifacts of only the last 4 builds
            ))
        }

        parameters {
            // Defines the parameters required for the build, allowing dynamic input during execution.
            string(name: 'COMPONENT', defaultValue: '', description: 'Specify the name of the application component to be deployed.')
            string(name: 'ENV', defaultValue: '', description: 'Specify the target environment for deployment.')
            string(name: 'VERSION', defaultValue: '', description: 'Specify the version of the application to be deployed.')
        }

        environment {
            // Sets up an environment variable to securely pass SSH credentials for remote access for ansible.
            SSH = credentials('366954ce-8f22-4117-b10d-0717e4aefcb9')
        }

        stages {
            stage('Update the version in PS') {
                steps {
                    // Updates the application version in AWS Parameter Store
                    sh '''
                    aws ssm put-parameter --name "${COMPONENT}.${ENV}.appVersion" --type "String" --value "${VERSION}" --overwrite
                    '''

                    script {
                        // Add a badge with environment, component, and version info
                        addShortText(text: "${ENV}-${COMPONENT}-${VERSION}")
                    }
                }
            }


            stage('Application Deployment') {
                steps {
                    sh '''
                    aws ec2 describe-instances --filters "Name=tag:Name,Values=${ENV}-${COMPONENT}" --query 'Reservations[*].Instances[*].PrivateIpAddress' --output text > inv
                    if [ -s inv ]; then
                        ansible-playbook -i inv main.yml -e component=${COMPONENT} -e env=${ENV} -e ansible_user=${SSH_USR} -e ansible_password=${SSH_PSW}
                    else
                        echo "Inventory file is empty. No instances found for ${ENV}-${COMPONENT}"
                        exit 1
                    fi
                    '''
                }
            }
        }


        post {
            always {
                // Ensures the workspace is cleaned up after the pipeline run to free disk space and avoid conflicts.
                cleanWs()
            }
        }

    }
}