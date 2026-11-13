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

plugins {
    kotlin("plugin.serialization")
}

kotlin {
    jvm()

    sourceSets {
        jvmMain {
            dependencies {
                val ktormVersion = providers.gradleProperty("ktormVersion").get()

                val serializationVersion = providers.gradleProperty("serializationVersion").get()

                api("org.ktorm:ktorm-core:$ktormVersion")

                api(project(":exkt-dto"))

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion")
            }
        }

        jvmTest {
            dependencies {
                val mockkVersion = providers.gradleProperty("mockkVersion").get()

                val coroutinesVersion = providers.gradleProperty("coroutinesVersion").get()

                implementation(kotlin("test-junit"))
                implementation("io.mockk:mockk:$mockkVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
            }
        }
    }
}