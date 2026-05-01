[![](https://maven.d1s.dev/api/badge/latest/releases/dev/d1s/exkt/exkt-test?color=40c14a&name=maven.d1s.dev&prefix=v)](https://maven.d1s.dev/#/releases/dev/d1s/exkt/exkt-test)

### Exkt for kotlin.test

This Exkt module provides handy utilities for kotlin.test. It mostly contain additional assertions.

### Installation

```kotlin
repositories {
    maven(url = "https://maven.d1s.dev/releases")
}

dependencies {
    val exktVersion: String by project

    implementation("dev.d1s.exkt:exkt-test:$exktVersion")
}
```

### Documentation

| Module API                              |
|-----------------------------------------|
| https://d1snin.github.io/exkt/exkt-test |