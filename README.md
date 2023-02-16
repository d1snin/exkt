[![](https://maven.d1s.dev/api/badge/latest/releases/dev/d1s/exkt/exkt-dto?color=40c14a&name=maven.d1s.dev&prefix=v)](https://maven.d1s.dev/#/releases/dev/d1s/exkt)


### Exkt (**K**o**t**lin **Ex**tensions)

Exkt (**K**o**t**lin **Ex**tensions) is a set of utilities and extensions for a variety of libraries and frameworks.

### Exkt modules

- [Exkt DTO](./exkt-dto)
- [Exkt for JUnit](./exkt-junit)
- [Exkt for Konform](./exkt-konform)
- [Exkt for Ktor Server](./exkt-ktor-server)
- [Exkt for Ktorm](./exkt-ktorm)
- [Exkt for PostgreSQL Java Driver](./exkt-postgres)

### Installation

```kotlin
repositories {
    maven(url = "https://maven.d1s.dev/releases")
}

dependencies {
    val exktVersion: String by project

    implementation("dev.d1s.exkt:exkt-{module}:$exktVersion")
}
```

### How to contribute

See [CONTRIBUTING.md](./CONTRIBUTING.md)

### Code of Conduct

See [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md)

### License

```
Copyright 2022-2023 Mikhail Titov

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```