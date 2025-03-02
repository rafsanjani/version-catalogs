rootProject.name = "versions"

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
        mavenLocal()
    }

    includeBuild("build-logic")
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
            from("io.github.rafsanjani:versions:2025.03.02")
        }
    }
}

include("versions")
include(":sample")
