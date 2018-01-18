package jnonino.devops.jenkins

import javaposse.jobdsl.dsl.DslFactory

/**
 * Created by jnonino on 5/19/17.
 */
class PipelineJob {

    private DslFactory dslFactory
    private String pipelineId
    private String pipelineName
    private String gitRemoteURL
    private String credentialsId

    PipelineJob(DslFactory dslFactory, String id, String displayName, String gitURL, String credentialsId) {
        this.dslFactory = dslFactory
        this.pipelineId = id
        this.pipelineName = displayName
        this.gitRemoteURL = gitURL
        this.credentialsId = credentialsId
    }

    def createJob() {
        this.dslFactory.multibranchPipelineJob(this.pipelineId) {
            displayName(this.pipelineName)
            branchSources {
                git {
                    // Sets credentials for authentication with the remote repository.
                    credentialsId(this.credentialsId)
                    // Sets a pattern for branches to exclude.
                    excludes('')
                    // Specifies a unique ID for this branch source.
                    //id(this.pipelineId)
                    // If set, ignores push notifications.
                    ignoreOnPushNotifications(true)
                    // Sets a pattern for branches to include.
                    includes('*')
                    // Sets the Git remote repository URL.
                    remote(this.gitRemoteURL)
                }
            }
            triggers {
                // Check for modifications in branches every 60 minutes
                periodic(60)
            }
        }
    }
}
