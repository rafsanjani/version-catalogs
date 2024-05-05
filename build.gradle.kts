import java.nio.file.Files
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.io.path.Path

val libraryVersion = "2024.05.05"
val libraryGroup = "io.github.rafsanjani"

group = libraryGroup
version = libraryVersion

plugins {
    `version-catalog`
    `maven-publish`
    signing
    alias(libs.plugins.nexus.publishing)
}

catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
    }
}

signing {
    sign(publishing.publications)
}

val publishingUsername: String = System.getenv("MAVEN_CENTRAL_USERNAME") ?: ""
val publishingPassword: String = System.getenv("MAVEN_CENTRAL_PASSWORD") ?: ""

nexusPublishing {
    repositories {
        sonatype {
            useStaging.set(true)

            username.set(publishingUsername)
            password.set(publishingPassword)

            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}

tasks.register("incrementVersionCode") {
    group = "Gradle Version Updater"
    description = "Updates the version code in build.gradle.kts file"

    doLast {
        incrementVersion()
    }
}

fun incrementVersion() {
    val configPath = "${project.rootDir.absolutePath}/build.gradle.kts"

    println(configPath)
    val fileContents = Files.readAllLines(Path(configPath))
        ?: throw IllegalStateException("Error reading libs.version.toml file from $configPath")


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

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["versionCatalog"])

            pom {
                name.set("version-catalog")
                description.set("Some personally curated android libraries")
                url.set("https://github.com/rafsanjani/versions")

                licenses {
                    license {
                        name.set("The MIT License (MIT)")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("foreverrafs")
                        name.set("Rafsanjani Abdul-Aziz")
                        email.set("foreverrafs@gmail.com")
                    }
                }
                organization {
                    name.set("Code Twisters Inc.")
                }

                scm {
                    connection.set("scm:git:git://github.com/rafsanjani/version-catalogs.git")
                    developerConnection.set("scm:git:ssh://git@github.com/rafsanjani/version-catalogs.git")
                    url.set("https://github.com/rafsanjani/version-catalogs")
                    name.set("version-catalog")
                    description.set("Some personally curated android libraries")
                    developers {
                        developer {
                            id.set("foreverrafs")
                            name.set("Rafsanjani")
                        }
                    }
                }
            }
        }
    }
}
