import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.foreverrafs.versions.convention"

gradlePlugin {
    val rootPackageName = "com.foreverrafs.versions"

    plugins {
        register("VersionUpdaterPlugin") {
            id = "com.foreverrafs.versionupdater"
            implementationClass = "$rootPackageName.VersionUpdaterPlugin"
        }
    }
}
