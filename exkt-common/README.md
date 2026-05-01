[![](https://maven.d1s.dev/api/badge/latest/releases/dev/d1s/exkt/exkt-common?color=40c14a&name=maven.d1s.dev&prefix=v)](https://maven.d1s.dev/#/releases/dev/d1s/exkt/exkt-common)

### Exkt - Common utilities.

This Exkt module provides common extensions for Kotlin.
The key rules for this module are:
- Depend only on Kotlin Standard Library.
- Provide abstract and general extensions.

### Installation

```kotlin
repositories {
    maven(url = "https://maven.d1s.dev/releases")
}

dependencies {
    val exktVersion: String by project

    implementation("dev.d1s.exkt:exkt-common:$exktVersion")
}
```

### Documentation

| Module API                                |
|-------------------------------------------|
| https://d1snin.github.io/exkt/exkt-common |