def call() {

    // Set build retention to keep only the last 3 builds
    properties([
            buildDiscarder(logRotator(
                    numToKeepStr: '3',   // Keep only the last 3 builds
                    artifactNumToKeepStr: '3' // Keep artifacts of only the last 3 builds
            ))
    ])

    node('workstation') {

        stage('Checkout SCM') {
//          Remove the old contain.
            sh "find . | sed -e '1d' | xargs rm -rf"

//          Fetch the tag Name if available.
            if (env.TAG_NAME ==~ ".*") {
                env.branch_name = "refs/tags/${env.TAG_NAME}"
            } else {
                env.branch_name = "${env.BRANCH_NAME}"
            }

//          Then checkout to Branch or Tag.
            checkout scmGit(
                    branches: [[name: branch_name]],
                    userRemoteConfigs: [[url: "https://github.com/abhijeet4022/${component}-v1"]]
            )
        }

//      If it's a Tag then do the Code Compilation and Release the software.
        if (env.TAG_NAME ==~ ".*") {
            common.CodeCompileAndBuilt()
            common.Release()
        } else {
//          If it's a main branch then do CodeCompileAndBuilt, UnitTest, CodeQuality and CodeSecurity.
            if (env.BRANCH_NAME == "main") {
                common.CodeCompileAndBuilt()
                common.UnitTest()
                common.CodeQuality()
                common.CodeSecurity()
            } else {
//              Otherwise only do CodeCompileAndBuilt, UnitTest and CodeQuality like Function branch not required security check.
                common.CodeCompileAndBuilt()
                common.UnitTest()
                common.CodeQuality()
            }
        }


    }


}