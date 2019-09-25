import cnservices.devops.jenkins.PipelineJob
import javaposse.jobdsl.dsl.DslFactory

PipelineJob example_job = new PipelineJob(this as DslFactory, 'EXAMPLE_JOB_ID', 'EXAMPLE_JOB_NAME',
                                          'EXAMPLE_JOB_GIT_REPO_URL', 'EXAMPLE_JOB_GIT_CRED_ID')
example_job.createJob()
