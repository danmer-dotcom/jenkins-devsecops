pipeline {
    agent { node('windows') }
    stages {
        stage('APPScan: DAST demo') {
            steps {
                bat "AppScanCMD e /su http://demo.testfire.net"
            }
        }
    }
}
