//import javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration
//import jenkins.model.GlobalConfiguration

// disable Job DSL script approval

pipeline {
    agent any
    stages {
        stage('Folders') {
            steps {
                jobDsl scriptText: 'job("folders")'
                jobDsl targets: ['folders.groovy'].join('\n'),
                removedJobAction: 'DELETE',
                removedViewAction: 'DELETE',
                lookupStrategy: 'SEED_JOB'
            }
        }
        stage('pipelines') {
            steps {
                jobDsl scriptText: 'job("pipelines")'
                jobDsl targets: ['pipelines.groovy'].join('\n'),
                removedJobAction: 'DELETE',
                removedViewAction: 'DELETE',
                lookupStrategy: 'SEED_JOB'
            }
       }
  }
    post {
        // Clean after build
        always {
            cleanWs(cleanWhenNotBuilt: false,
            deleteDirs: true,
            disableDeferredWipeout: true,
            notFailBuild: true,
            patterns: [[pattern: '.gitignore', type: 'INCLUDE'],
            [pattern: '.propsfile', type: 'EXCLUDE']])
        }
    }
}
