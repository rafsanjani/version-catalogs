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
            from("io.github.rafsanjani:versions:2025.06.15")
        }
    }
}

include("versions")
include(":sample")
