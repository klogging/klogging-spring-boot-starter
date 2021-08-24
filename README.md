# Spring Boot starter for Klogging

## Set up Gradle:

```kotlin
dependencies {
    implementation("org.springframework.boot:spring-boot-starter") {
        exclude(group = "ch.qos.logback")
    }
    implementation("io.klogging:klogging-spring-boot-starter:0.1.3")
    // Other runtime dependencies.
    
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "ch.qos.logback")
    }
    // Other test dependencies.
}
```

## Configure Klogging

Put a `logging.json` file in the `src/main/resources` directory of the project.

Here is a simple one for logging to the console.

```json
{
  "sinks": {
    "stdout": {
      "renderWith": "RENDER_SIMPLE",
      "dispatchTo": "STDOUT"
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
