// Compile Source Code and Build Artifacts.
def CodeCompileAndBuilt() {
    stage('Code Compile And Built Phase') {
        echo 'CodeCompileAndBuilt'
    }
}

// Executing Unit Testing Phase to Validate Code Functions.
def UnitTest() {
    stage('Do Unit Test') {
        echo 'UnitTest'
    }
}

// Perform Static Code Analysis for Quality.
def CodeQuality() {
    stage("Check Code Quality") {
        echo 'CodeQuality'
    }
}

// Executing Security Scanning to Identify Vulnerabilities.
def CodeSecurity() {
    stage('Do Code Security') {
        echo 'CodeSecurity'
    }
}

// Packaging and Releasing the Build for Deployment.
def Release() {
    stage('Software Release') {
        echo 'Release'
    }
}

