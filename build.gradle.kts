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

import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.AbstractDokkaTask
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.gradle.DokkaTaskPartial
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("multiplatform")
    id("java-library")
    id("maven-publish")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")
    id("com.github.ben-manes.versions")
}

buildscript {
    dependencies {
        val dokkaVersion: String by project

        classpath("org.jetbrains.dokka:dokka-base:$dokkaVersion")
    }
}

allprojects {
    apply {
        plugin("org.jetbrains.kotlin.multiplatform")
        plugin("maven-publish")
        plugin("org.jetbrains.dokka")
        plugin("org.jetbrains.kotlinx.kover")
    }

    val projectGroup: String by project
    val projectVersion: String by project

    group = projectGroup
    version = projectVersion

    repositories {
        mavenCentral()
    }

    publishing {
        repositories {
            maven {
                name = "mavenD1sDevRepository"

                val channel = if (isDevVersion) {
                    "snapshots"
                } else {
                    "releases"
                }

                url = uri("https://maven.d1s.dev/$channel")

                credentials {
                    username = System.getenv("MAVEN_D1S_DEV_USERNAME")
                    password = System.getenv("MAVEN_D1S_DEV_PASSWORD")
                }
            }
        }

        publications.all {
            if (isDevVersion) {
                val commitShortSha = System.getenv("GIT_SHORT_COMMIT_SHA")

                commitShortSha?.let {
                    version = "$version-$it"
                }
            }
        }
    }

    tasks.withType<Test> {
        testLogging {
            events.addAll(
                listOf(
                    TestLogEvent.FAILED,
                    TestLogEvent.PASSED,
                    TestLogEvent.SKIPPED
                )
            )
        }
    }

    tasks.withType<DokkaTaskPartial> {
        dokkaSourceSets {
            configureEach {
                val moduleDocsPath: String by project

                includes.setFrom(moduleDocsPath)
            }
        }

        pluginConfiguration()
    }

    kotlin {
        explicitApi()
    }
}

kotlin {
    jvm {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    js {
        browser()
        nodejs()
    }
}

tasks.withType<DokkaMultiModuleTask> {
    includes.setFrom("README.md")

    pluginConfiguration()
}

val Project.isDevVersion get() = this.version.toString().endsWith("-dev")

fun AbstractDokkaTask.pluginConfiguration() {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "Copyright (c) 2022-2023 Mikhail Titov"
    }
}
