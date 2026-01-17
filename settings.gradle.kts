rootProject.name = "versions"

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        mavenLocal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        mavenLocal()
    }

    versionCatalogs {
        create("remoteLibs") {
            from("io.github.rafsanjani:versions:2026.01.17")
        }
    }
}

include(":sample")
