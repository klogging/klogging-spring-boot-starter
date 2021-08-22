# Spring Boot starter for Klogging

Gradle usage:

```kotlin
dependencies {
    implementation("org.springframework.boot:spring-boot-starter") {
        exclude(group = "ch.qos.logback")
    }
    implementation("io.klogging:klogging-spring-boot-starter:0.1.2")
    // Other runtime dependencies.
    
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "ch.qos.logback")
    }
    // Other test dependencies.
}
```
