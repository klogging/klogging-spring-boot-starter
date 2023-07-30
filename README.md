# Spring Boot starter for Klogging

## The code for this library is now [here](https://github.com/klogging/klogging/tree/main/klogging-spring-boot-starter) and this repository is archived

See [Klogging documentation](https://klogging.io/docs/java/spring-boot) for more details.

## Set up Gradle:

```kotlin
dependencies {
    implementation("org.springframework.boot:spring-boot-starter") {
        exclude(group = "ch.qos.logback")
    }
    implementation("io.klogging:klogging-spring-boot-starter:0.5.0")
    // Other runtime dependencies.
    
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "ch.qos.logback")
    }
    // Other test dependencies.
}
```

## Configure Klogging

Put a `klogging.json` file in the `src/main/resources` directory of the project.

Here is a simple one for logging to the console.

```json
{
  "sinks": {
    "stdout": {
      "renderWith": "RENDER_ANSI",
      "sendTo": "STDOUT"
    }
  },
  "logging": [
    {
      "levelRanges": [
        {
          "fromMinLevel": "INFO",
          "toSinks": [
            "stdout"
          ]
        }
      ]
    }
  ]
}
```
