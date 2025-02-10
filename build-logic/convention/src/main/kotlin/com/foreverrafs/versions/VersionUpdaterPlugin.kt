package com.foreverrafs.versions

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.nio.file.Files
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.io.path.Path

class VersionUpdaterPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("incrementVersionCode") {
            group = "Gradle Version Updater"
            description = "Updates the version code in build.gradle.kts file"
            doLast {
                incrementVersion(target)
            }
        }
    }

    private fun incrementVersion(project: Project) {
        val configPath = "${project.rootDir.absolutePath}/build.gradle.kts"

        println(configPath)
        val fileContents = Files.readAllLines(Path(configPath))
            ?: throw IllegalStateException("Error reading build.gradle.kts file from $configPath")


        val predicate = { str: String -> str.contains("libraryVersion") }

        val oldVersion = fileContents
            .firstOrNull(predicate)
            ?.split("=")
            ?.last()
            ?.replace("\"", "")
            ?.trim()

        println("Old version: $oldVersion")

        val updatedVersion = DateTimeFormatter.ofPattern("yyyy.MM.dd").format(LocalDate.now())
        println("New version: $updatedVersion")

        if (oldVersion == null) {
            println("ERROR: Error parsing version number from build.gradle.kts file")
            return
        }

        for (i in fileContents.indices) {
            if (predicate(fileContents[i])) {
                fileContents[i] = "val libraryVersion = \"$updatedVersion\""
                break
            }
        }

        Files.write(Path(configPath), fileContents)
    }
}