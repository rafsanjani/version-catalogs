plugins {
    `kotlin-dsl`
    `version-catalog`
    `maven-publish`
}

group = "com.foreverrafs"

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = rootProject.name
            version = "0.0.2"
            from(components.getByName("versionCatalog"))

            pom {
                name.set("versions")
                description.set(project.description)
                url.set("https://www.foreverrafs.com/versions")
            }
        }
    }
}

catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
    }
}
