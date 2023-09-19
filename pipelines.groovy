pipelineJob('DAST/test') {
  definition {
    cpsScm {
      scm {
        git {
          remote {
            url('https://github.com/rafstef/demo-dast.git')
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
            url('https://github.com/rafstef/demo-dast.git')
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
            url('https://github.com/rafstef/demo-dast.git')
            scriptPath("pipelines/dt.groovy")
          }
          branch('*/master')
        }
      }
      lightweight()
    }
  }
}
