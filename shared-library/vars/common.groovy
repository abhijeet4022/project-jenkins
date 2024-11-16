// Compile Source Code and Build Artifacts.
def CodeCompileAndBuilt() {


//  For Payment and Frontend no need of code compilation.
    if (env.codeType == "python" || env.codeType == "static") {
        return "Return, Do not need Compilation"
    }

    stage('Code Compile And Built Phase') {
//      For Shipping Component.
        if (env.codeType == "maven") {
            sh '/home/centos/mavan/bin/mvn clean package'
        }

//      For Catalogue, User and Cart Component
        if (env.codeType == "nodejs") {
            sh 'npm install'
        }

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

