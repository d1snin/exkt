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

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_17.majorVersion
        }
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                val ktorVersion: String by project

                val koinVersion: String by project

                val kmLogVersion: String by project

                implementation("org.lighthousegames:logging:$kmLogVersion")

                api("io.ktor:ktor-server:$ktorVersion")
                api("io.ktor:ktor-server-host-common:$ktorVersion")

                api("io.insert-koin:koin-ktor:$koinVersion")
                implementation("io.insert-koin:koin-logger-slf4j:$koinVersion")

                implementation(project(":exkt-common"))
            }
        }

        val jvmTest by getting {
            dependencies {
                val mockkVersion: String by project

                val koinVersion: String by project

                implementation(kotlin("test-junit"))
                implementation("io.mockk:mockk:$mockkVersion")
                implementation("io.insert-koin:koin-test:$koinVersion")
            }
        }
    }
}