package jnonino.devops.jenkins

import groovy.io.FileType
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.dsl.JobManagement
import javaposse.jobdsl.plugin.JenkinsJobManagement
import org.junit.ClassRule
import org.jvnet.hudson.test.JenkinsRule
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by jnonino on 5/22/17.
 * Tests if all DSL scripts in jobs folder compile
 */
class JobScriptsSpec extends Specification {

    @Shared
    @ClassRule
    JenkinsRule jenkinsRule = new JenkinsRule()

    @Unroll
    void 'test script #file.name'(File file) {
        given:
        JobManagement jm = new JenkinsJobManagement(System.out, [:], new File('.'))
        jm.requirePlugin('job-dsl', false)
        jm.requirePlugin('workflow-multibranch', false)
        jm.requirePlugin('gradle', false)
        jm.requirePlugin('git', false)
        jm.requirePlugin('checkstyle', false)
        jm.requirePlugin('pmd', false)
        jm.requirePlugin('findbugs', false)
        jm.requirePlugin('tasks', false)
        jm.requirePlugin('jacoco', false)
        jm.requirePlugin('cobertura', false)
        jm.requirePlugin('nested-view', false)
        jm.requirePlugin('greenballs', false)
        jm.requirePlugin('ci-game', false)
        jm.requirePlugin('emotional-jenkins-plugin', false)

        when:
        new DslScriptLoader(jm).runScript(file.text)

        then:
        noExceptionThrown()

        where:
        file << jobFiles
    }

    static List<File> getJobFiles() {
        List<File> files = []
        new File('jobs').eachFileRecurse(FileType.FILES) {
            if (it.name.endsWith('.groovy')) {
                files << it
            }
        }
        files
    }
}
