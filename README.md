[![](https://maven.d1s.dev/api/badge/latest/releases/dev/d1s/exkt/exkt-dto?color=40c14a&name=maven.d1s.dev&prefix=v)](https://maven.d1s.dev/#/releases/dev/d1s/exkt)

### Exkt (**K**o**t**lin **Ex**tensions)

Exkt (**K**o**t**lin **Ex**tensions) is a set of utilities and extensions for a variety of libraries and frameworks.

### Exkt modules

Each Exkt has a README describing it. Here is the list of all available modules:

- [Exkt DTO][exkt_dto]
- [Exkt for JUnit][exkt_junit]
- [Exkt for Konform][exkt_konform]
- [Exkt for Ktor Server][exkt_ktor_server]
- [Exkt for Ktorm][exkt_ktorm]
- [Exkt for PostgreSQL Java Driver][exkt_postgres]

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

See [CONTRIBUTING.md][contribution_guide]

### Code of Conduct

See [CODE_OF_CONDUCT.md][code_of_conduct]

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

[exkt_dto]: https://github.com/d1snin/exkt/tree/main/exkt-dto
[exkt_junit]: https://github.com/d1snin/exkt/tree/main/exkt-junit
[exkt_konform]: https://github.com/d1snin/exkt/tree/main/exkt-konform
[exkt_ktor_server]: https://github.com/d1snin/exkt/tree/main/exkt-ktor-server
[exkt_ktorm]: https://github.com/d1snin/exkt/tree/main/exkt-ktorm
[exkt_postgres]: https://github.com/d1snin/exkt/tree/main/exkt-postgres

[contribution_guide]: https://github.com/d1snin/exkt/blob/main/CONTRIBUTING.md
[code_of_conduct]: https://github.com/d1snin/exkt/blob/main/CODE_OF_CONDUCT.md