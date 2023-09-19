pipeline {
    agent any
    stages {
        stage('APPScan: DAST demo') {
            agent { node { label 'windows' } }
            steps {
                bat "AppScanCMD e /su http://demo.testfire.net"
            }
        }
    }
}