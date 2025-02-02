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
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        mavenLocal()
    }

    versionCatalogs {
        create("remoteLibs") {
            from("io.github.rafsanjani:versions:2025.02.02")
        }
    }
}

include("versions")
include(":sample")
