rootProject.name = "klogging-spring-boot-starter"

pluginManagement {
    val nexusPublishPluginVersion: String by settings

    plugins {
        id("io.github.gradle-nexus.publish-plugin") version nexusPublishPluginVersion
    }
}
