group = "io.github.rafsanjani"
version = "0.0.8"

plugins {
    `version-catalog`
    `maven-publish`
    signing
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
                        name.set("Rafsanjani")
                    }
                }
                organization {
                    name.set("Rafsanjani")
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

    repositories {
        maven {
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            version = "0.0.8"
            group = "io.github.rafsanjani"

            credentials {
                username = publishingUsername
                password = publishingPassword
            }
        }
    }
}
