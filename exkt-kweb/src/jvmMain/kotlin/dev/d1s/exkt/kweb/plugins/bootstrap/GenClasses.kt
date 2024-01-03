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

package dev.d1s.exkt.kweb.plugins.bootstrap

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

private enum class Mode {
    MAIN, ICONS
}

private val client = HttpClient.newHttpClient()

private val path = Paths.get("out.kt")

internal fun main(args: Array<String>) {
    val mode = Mode.entries.find {
        it.name.lowercase() == args.firstOrNull()
    } ?: error("Mode is undefined.")

    val classes = fetchClasses(mode)

    prepareOutput()
    writeSourceFile(mode, classes)
}

private fun fetchClasses(mode: Mode): List<String> {
    val source = when (mode) {
        Mode.MAIN -> "https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.css"
        Mode.ICONS -> "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
    }

    val request = HttpRequest.newBuilder(URI(source)).build()
    val response = client.send(request, BodyHandlers.ofString()).body()

    val classRegex = "[\\\\A\\s]+\\.[a-z0-9-]+".toRegex()

    return classRegex.findAll(response)
        .map {
            it.value
                .trim()
                .removePrefix(".")
        }
        .toSet()
        .filterNot {
            it.first().isDigit()
        }
}

private fun prepareOutput() {
    Files.deleteIfExists(path)
    Files.createFile(path)
}

private fun writeSourceFile(mode: Mode, classes: List<String>) {
    val classDefinition = when (mode) {
        Mode.MAIN -> "BootstrapClasses"
        Mode.ICONS -> "BootstrapIconsClasses"
    }

    prepareSourceFile(classDefinition)

    classes.forEach {
        writeProperty(it, classDefinition)
    }

    finishSourceFile()
}

private fun prepareSourceFile(classDefinition: String) {
    writeOutput(
        "// !!! THIS FILE WAS AUTOMATICALLY GENERATED VIA dev.d1s.exkt.kweb.plugins.bootstrap.GenClassesKt DO NOT MODIFY !!!\n\n"
    )

    writeOutput("package dev.d1s.exkt.kweb.plugins.bootstrap\n\n")
    writeOutput("import kweb.AttributeBuilder\nimport kweb.classes\n\n")
    writeOutput("public class $classDefinition : AttributeBuilder() {\n\n")
}

private fun writeProperty(classname: String, classDefinition: String) {
    val propertyName =
        classname.split("-")
            .mapIndexed { index, s ->
                if (index != 0) {
                    s.replaceFirstChar {
                        it.uppercase()
                    }
                } else {
                    s
                }
            }.joinToString("")

    writeOutput(
        "    public val $propertyName: $classDefinition\n" +
                "        get() {\n" +
                "            classes(\"$classname\")\n" +
                "            return this\n" +
                "        }\n\n"
    )
}

private fun finishSourceFile() {
    writeOutput("}")
}

private fun writeOutput(text: String) {
    Files.writeString(path, text, StandardOpenOption.APPEND)
}