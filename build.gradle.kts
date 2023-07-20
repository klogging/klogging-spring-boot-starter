/*

   Copyright 2021-2023 Michael Strasser.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/

plugins {
    `maven-publish`
    `java-library`
    signing
    id("io.github.gradle-nexus.publish-plugin")
}

group = "io.klogging"
version = "0.3.5"
description = "Starter for using Klogging for logging. An alternative to spring-boot-starter-logging"

val kloggingVersion: String by project
val slf4jKloggingVersion: String by project

repositories {
    mavenCentral()
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
            pom.withXml {
                asNode().apply {
                    appendNode("dependencies").apply {
                        appendNode("dependency").apply {
                            appendNode("groupId", "io.klogging")
                            appendNode("artifactId", "klogging-jvm")
                            appendNode("version", kloggingVersion)
                            appendNode("scope", "compile")
                        }
                        appendNode("dependency").apply {
                            appendNode("groupId", "io.klogging")
                            appendNode("artifactId", "slf4j-klogging")
                            appendNode("version", slf4jKloggingVersion)
                            appendNode("scope", "compile")
                        }
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
