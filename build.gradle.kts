val libraryVersion = "0.2.3"
val libraryGroup = "io.github.rafsanjani"

group = libraryGroup
version = libraryVersion

plugins {
    `version-catalog`
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin").version("1.3.0")
}

catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
    }
}

signing {
    sign(publishing.publications)
}

val publishingUsername: String? = System.getenv("MAVEN_CENTRAL_USERNAME")
val publishingPassword: String? = System.getenv("MAVEN_CENTRAL_PASSWORD")

if (publishingUsername == null || publishingPassword == null) {
    error("MAVEN_CENTRAL_USERNAME and MAVEN_CENTRAL_PASSWORD not found!!")
}

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
