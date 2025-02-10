import com.vanniktech.maven.publish.SonatypeHost

val libraryVersion = "2025.02.09"
val libraryGroup = "io.github.rafsanjani"

group = libraryGroup
version = libraryVersion

plugins {
    `version-catalog`
    id("com.foreverrafs.versionupdater")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
    }
}


val publishingUsername: String = System.getenv("MAVEN_CENTRAL_USERNAME") ?: ""
val publishingPassword: String = System.getenv("MAVEN_CENTRAL_PASSWORD") ?: ""


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, true)
    signAllPublications()
    coordinates(libraryGroup, "versions", libraryVersion)

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
