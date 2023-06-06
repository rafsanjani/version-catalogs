plugins {
    `kotlin-dsl`
    `version-catalog`
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.foreverrafs"
            artifactId = "versions"
            version = "0.0.1"
            from(components.getByName("versionCatalog"))
        }
    }
}

catalog {
    versionCatalog {
        from(files("gradle/libs.versions.toml"))
    }
}
