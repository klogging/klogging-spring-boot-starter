plugins {
    `maven-publish`
    `java-library`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

group = "io.klogging"
version = "0.1.0-SNAPSHOT"
description = "Starter for using Klogging for logging. An alternative to spring-boot-starter-logging"

repositories {
    mavenCentral()
}

dependencies {
    api("io.klogging:klogging-jvm:0.3.0")
    api("io.klogging:slf4j-klogging:0.1.0")
}

publishing {
    publications {
        create<MavenPublication>("starterPom") {
            pom {
                name.set("klogging-spring-boot-starter")
                description.set("Spring Boot starter for Klogging logging library")
                url.set("https://klogging.io/")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                    developers {
                        developer {
                            id.set("mjstrasser")
                            name.set("Michael Strasser")
                            email.set("mjstrasser@klogging.io")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/klogging/klogging-spring-boot-starter.git")
                        url.set("https://github.com/klogging/klogging-spring-boot-starter")
                    }
                }
            }
        }
    }
}

nexusPublishing {
    val usernameEnvironmentVariableName = "OSSRH_USERNAME"
    val passwordEnvironmentVariableName = "OSSRH_PASSWORD"
    val repoUsername = System.getenv(usernameEnvironmentVariableName)
    val repoPassword = System.getenv(passwordEnvironmentVariableName)
    repositories {
        create("sonatype") {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
            username.set(repoUsername)
            password.set(repoPassword)
        }
    }
}

signing {
    val signingKeyId = System.getenv("SIGNING_KEY_ID")
    val signingKey = System.getenv("SIGNING_KEY") // ASCII-armoured
    val signingPassword = System.getenv("SIGNING_PASSWORD")
    useInMemoryPgpKeys(signingKeyId, signingKey, signingPassword)
    sign(publishing.publications["starterPom"])
}
