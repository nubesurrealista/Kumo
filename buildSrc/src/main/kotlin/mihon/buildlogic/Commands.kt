package mihon.buildlogic

import org.gradle.api.Project

fun Project.getCommitCount(): String {
    return runCommand("git rev-list --count HEAD")
}

fun Project.getGitSha(): String {
    return runCommand("git rev-parse --short=7 HEAD")
}

private fun Project.runCommand(command: String): String {
    return providers.exec {
        commandLine = command.split(" ")
    }
        .standardOutput
        .asText
        .get()
        .trim()
}