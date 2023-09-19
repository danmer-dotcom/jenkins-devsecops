pipeline {
    agent { label 'master' }
    
    stages {
        stage('Preparazione') {
            steps {
                script {
                    def mavenHome = tool 'Maven'
                    def mvnCmd = "${mavenHome}/bin/mvn"
                    
                    sh "rm -rf ${workspace}/*"
                }
            }
        }
        
        stage('Clonazione Git e Build') {
            steps {
                script {
                    dir(path: "${workspace}") {
                        git url: 'https://github.com/rimerosolutions/maven-wrapper-example.git', branch: 'master'
                        def mvnHome = tool 'Maven'
                        def mvnCmd = "${mvnHome}/bin/mvn"
                        
                        sh "${mvnCmd} clean install"
                    }
                }
            }
        }
        
        stage('Esegui AppScan') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'SAST-KEY-SECRET', variable: 'SAST-KEY-SECRET'),string(credentialsId: 'sast-api-key-secret', variable: 'SAST-API-KEY-SECRET')]) {
                    def apiKey = 'SAST-API-KEY-SECRET'
                    def keySecret = 'SAST-KEY-SECRET'
                    def applicationId = '6e5d0317-bd88-41a7-82af-00bab1075a48'
                    def credentialsId = 'appscan'
                    def appName = '6e5d0317-bd88-41a7-82af-00bab1075a489089'
                    def scanSpeed = 'fast'
                    
                    appscan application: applicationId, credentials: credentialsId, name: appName,
                            scanner: static_analyzer(hasOptions: false, scanSpeed: scanSpeed, target: "${env.WORKSPACE}"+"target/"+'maven-wrapper-example-0.0.1-SNAPSHOT.jar'),
                            type: 'Static Analyzer'
                }
            }
        }
    }
}
}

