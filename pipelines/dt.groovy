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
                        git url: 'https://github.com/danmer-dotcom/demo-maven-wrapper-example.git', branch: 'master'
                        def mvnHome = tool 'Maven'
                        def mvnCmd = "${mvnHome}/bin/mvn"
                        
                        sh "${mvnCmd} clean install -U"
                    }
                }
            }
        }
        
        stage('Esegui AppScan') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dt-api-key', variable: 'SECRET')]) {
                    
                     sh '''
                     
                     curl -X "POST" "http://51.38.118.159:8081/api/v1/bom" \
                          -H 'Content-Type: multipart/form-data' \
                          -H "X-Api-Key: $SECRET" \
                          -F "autoCreate=true" \
                          -F "projectName=test-pipeline-groovy" \
                          -F "projectVersion=1.0" \
                          -F "bom=@target/bom.xml"
                     '''
                    }
                }
            }
        }
    }
}


