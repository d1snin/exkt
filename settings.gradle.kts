/*
 * Copyright 2022-2023 Mikhail Titov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

rootProject.name = "exkt"

pluginManagement {
    plugins {
        val kotlinVersion: String by settings

        val dokkaVersion: String by settings

        val koverVersion: String by settings

        val versionsPluginVersion: String by settings

        kotlin("jvm") version kotlinVersion

        id("org.jetbrains.dokka") version dokkaVersion

        id("org.jetbrains.kotlinx.kover") version koverVersion

        id("com.github.ben-manes.versions") version versionsPluginVersion
    }
}

include(
    "exkt-dto",
    "exkt-junit",
    "exkt-konform",
    "exkt-ktor-server",
    "exkt-ktor-server-postgres-support",
    "exkt-ktorm",
    "exkt-postgres"
)