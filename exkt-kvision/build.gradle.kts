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
    js {
        browser()
        nodejs()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                val kvisionVersion: String by project

                val coroutinesVersion: String by project

                api("io.kvision:kvision:$kvisionVersion")

                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                implementation(project(":exkt-common"))
            }
        }
    }
}