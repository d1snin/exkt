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

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("org.jetbrains.dokka")
    id("org.jetbrains.kotlinx.kover")
    id("io.github.ben-manes.versions")
}

/**
 * JDK used to build the project (provisioned via the Foojay toolchain resolver).
 */
val buildJdkVersion = 25

/**
 * JVM bytecode/API level of the published artifacts.
 */
val targetJvmVersion = 17

allprojects {
    apply {
        plugin("org.jetbrains.kotlin.multiplatform")
        plugin("maven-publish")
        plugin("org.jetbrains.dokka")
        plugin("org.jetbrains.kotlinx.kover")
    }

    val projectGroup = providers.gradleProperty("projectGroup").get()
    val projectVersion = providers.gradleProperty("projectVersion").get()

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

    tasks.withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(targetJvmVersion.toString()))
            freeCompilerArgs.add("-Xjdk-release=$targetJvmVersion")
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(targetJvmVersion)
    }

    dokka {
        dokkaSourceSets.configureEach {
            val moduleDocsPath = providers.gradleProperty("moduleDocsPath").get()

            if (project != rootProject) {
                includes.from(moduleDocsPath)
            }
        }

        pluginsConfiguration.html {
            footerMessage.set("Copyright (c) 2022-2024 Mikhail Titov")
        }
    }

    kotlin {
        jvmToolchain(buildJdkVersion)

        explicitApi()
    }
}

kotlin {
    jvm()

    js {
        browser()
        nodejs()
    }
}

dependencies {
    subprojects.forEach {
        dokka(project(it.path))
    }
}

dokka {
    dokkaPublications.html {
        includes.from("README.md")
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

val Project.isDevVersion get() = this.version.toString().endsWith("-dev")

fun isNonStable(version: String): Boolean =
    Regex("(?i).*[.-](alpha|beta|rc|cr|m|eap|dev|snapshot|preview)[.\\d-]*.*").matches(version)
