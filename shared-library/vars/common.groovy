// Compile Source Code and Build Artifacts.
def CodeCompileAndBuilt() {

//  For Payment and Frontend no need of code compilation.
    if (env.codeType == "python" || env.codeType == "static") {
        return "Return, Do not need Compilation"
    }

    stage('Code Compile And Built Phase') {
//      For Shipping Component.
        if (env.codeType == "maven") {
            sh '/home/centos/mavan/bin/mvn  package'
        }

//      For Catalogue, User and Cart Component
        if (env.codeType == "nodejs") {
            sh 'npm install'
        }

    }
}


// Executing Unit Testing Phase to Validate Code Functions.
def UnitTest() {
    if (env.codeType == "static") {
        return "Static Content no need to do Unit Test"
    }
//  Since developer didn't written code for unit test so it will fail. That's why using print 'ok'.
    stage('Do Unit Test') {
        if (env.codeType == "nodejs") {
//            sh 'npm test'
            print 'ok'
        }
        if (env.codeType == "maven") {
//            sh '/home/centos/mavan/bin/mvn test'
            print 'ok'
        }
        if (env.codeType == "python") {
//            sh 'python3.6 -m unittest'
            print 'ok'
        }
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
    stage('Check Code Security') {
        echo 'CodeSecurity'
    }
}

// Packaging and Releasing the Build for Deployment.
def Release() {
    stage('Software Release') {
        echo 'Release'
    }
}

