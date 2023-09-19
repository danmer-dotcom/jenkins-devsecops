pipelineJob('DAST/test') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('https://github.com/rafstef/jenkins-devsecops.git')
            scriptPath("pipelines/dast.groovy")
          }
          branch('*/master')
        }
      }
      lightweight()
    }
  }
}
pipelineJob('SAST/test') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('https://github.com/rafstef/jenkins-devsecops.git')
            scriptPath("pipelines/sast.groovy")
          }
          branch('*/master')
        }
      }
      lightweight()
    }
  }
}
pipelineJob('DT/test') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('https://github.com/rafstef/jenkins-devsecops.git')
            scriptPath("pipelines/dt.groovy")
          }
          branch('*/master')
        }
      }
      lightweight()
    }
  }
}
