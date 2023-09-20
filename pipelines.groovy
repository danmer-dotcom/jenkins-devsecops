pipelineJob('DAST/test-dast') {
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
pipelineJob('SAST/test-sast') {
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
pipelineJob('DT/test-dt') {
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
