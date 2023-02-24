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

package dev.d1s.exkt.common

/**
 * @see padding
 */
public class PaddingConfig {
    public var top: Int = 0
    public var bottom: Int = 0
    public var left: Int = 0
    public var right: Int = 0

    internal fun validate() {
        arrayOf(top, bottom, left, right).forEach {
            require(it >= 0) {
                "Padding value must be greater or equal to zero."
            }
        }
    }
}

/**
 * Returns transformed string with padding specified by [PaddingConfig].
 * Example usage:
 * ```kotlin
 * val transformedString = "test".padding {
 *     top = 2
 *     bottom = 1
 *     left = 4
 *     right = 3
 * }
 * ```
 * Result:
 * ```plain
 * _
 * _
 * ____test___
 * _
 * ```
 */
public fun String.padding(padding: PaddingConfig.() -> Unit): String {
    val config = PaddingConfig().apply(padding)

    config.validate()

    val topPadding = "\n".repeat(config.top)
    val bottomPadding = "\n".repeat(config.bottom)

    val leftPadding = " ".repeat(config.left)
    val rightPadding = " ".repeat(config.right)

    val middlePart = split("\n").joinToString("\n") {
        leftPadding + it + rightPadding
    }

    return topPadding + middlePart + bottomPadding
}

/**
 * Returns transformed string with padding specified by [value]. Padding is applied in all directions.
 * Example usage:
 * ```kotlin
 * val transformedString = "test".padding(5)
 * ```
 * Result:
 * ```plain
 * _
 * _
 * _
 * _
 * _
 * _____test_____
 * _
 * _
 * _
 * _
 * _
 * ```
 */
public fun String.padding(value: Int): String = padding {
    top = value
    bottom = value
    left = value
    right = value
}

/**
 * Returns [this] string if it's not `null`. Empty string otherwise.
 */
public fun String?.thisOrEmpty(): String = this ?: ""

/**
 * Replaces placeholders in this string.
 * Uses the following placeholder format: `{placeholder_name}`.
 * Placeholders can be duplicated.
 *
 * Example usage:
 * ```kotlin
 * val template = "Name: {name}; Surname: {surname}; Full name: {name} {surname}"
 *
 * template.replacePlaceholders("name" to "Josh", "surname" to "Long")
 * // "Name: Josh; Surname: Long; Full name: Josh Long"
 * ```
 */
public fun String.replacePlaceholders(vararg replacements: Pair<String, String>): String =
    replacements.fold(this) { acc, replacement ->
        acc.replace("{${replacement.first}}", replacement.second)
    }

/**
 * Shortcut for `replacePlaceholders("id" to replacement)`.
 *
 * @see replacePlaceholders
 */
public fun String.replaceIdPlaceholder(replacement: String): String =
    replacePlaceholders("id" to replacement)

/**
 * Returns `true` if this string has whitespace characters. `false` otherwise.
 */
public fun String.hasWhitespace(): Boolean = this.contains("\\s".toRegex())

/**
 * Shortcut for `joinToString(" ")`
 */
public fun Iterable<String>.toSpaceDelimitedString(): String = this.joinToString(" ")

/**
 * Returns the lengthiest string in this collection or `null` if there are no elements.
 */
public fun Iterable<String>.lengthiestString(): String? = this.maxByOrNull {
    it.length
}
