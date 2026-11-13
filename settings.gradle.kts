/*
 * Copyright 2022-2024 Mikhail Titov
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

pluginManagement {
    plugins {
        val kotlinVersion = providers.gradleProperty("kotlinVersion").get()

        val dokkaVersion = providers.gradleProperty("dokkaVersion").get()

        val koverVersion = providers.gradleProperty("koverVersion").get()

        val versionsPluginVersion = providers.gradleProperty("versionsPluginVersion").get()

        kotlin("multiplatform") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion

        id("org.jetbrains.dokka") version dokkaVersion

        id("org.jetbrains.kotlinx.kover") version koverVersion

        id("io.github.ben-manes.versions") version versionsPluginVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "exkt"

include(
    "exkt-common",
    "exkt-dto",
    "exkt-test",
    "exkt-konform",
    "exkt-ktor-server",
    "exkt-ktor-server-koin",
    "exkt-ktor-server-postgres-support",
    "exkt-kvision",
    "exkt-kweb",
    "exkt-ktorm",
    "exkt-postgres"
)