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

package dev.d1s.exkt.common

import kotlin.test.*

class TextTest {

    @Test
    fun `PaddingConfig must have valid default values`() {
        val paddingConfig = PaddingConfig()

        with(paddingConfig) {
            assertEquals(0, top)
            assertEquals(0, bottom)
            assertEquals(0, left)
            assertEquals(0, right)
        }
    }

    @Test
    fun `PaddingConfig must throw IllegalArgumentException on validation`() {
        val paddingConfig = PaddingConfig().apply {
            top = -1
        }

        val failure = assertFailsWith<IllegalArgumentException> {
            paddingConfig.validate()
        }

        assertEquals("Padding value must be greater or equal to zero.", failure.message)
    }

    @Test
    fun `padding extension must apply valid padding`() {
        val transformedString = "test".padding {
            top = 3
            bottom = 2
            left = 4
            right = 5
        }

        assertEquals(
            """
            |
            |
            |
            |    test     
            |
            |
            """.trimMargin(),
            transformedString
        )
    }

    @Test
    fun `padding extension must apply valid padding in all directions`() {
        val transformedString = "test".padding(3)

        assertEquals(
            """
            |
            |
            |
            |   test   
            |
            |
            |
            """.trimMargin(),
            transformedString
        )
    }

    @Test
    fun `thisOrEmpty must return empty string`() {
        val nullString: String? = null

        assertEquals("", nullString.thisOrEmpty())
    }

    @Test
    fun `thisOrEmpty must return this string`() {
        assertEquals("test", "test".thisOrEmpty())
    }

    @Test
    fun `replacePlaceholders must replace all placeholders`() {
        val template = "Name: {name}; Surname: {surname}; Full name: {name} {surname}"

        val actual = template.replacePlaceholders("name" to "Josh", "surname" to "Long")

        assertEquals("Name: Josh; Surname: Long; Full name: Josh Long", actual)
    }

    @Test
    fun `replaceIdPlaceholder must replace 'id' placeholder`() {
        val template = "User ID: {id}"

        val actual = template.replaceIdPlaceholder("test")

        assertEquals("User ID: test", actual)
    }

    @Test
    fun `hasWhitespace must return true`() {
        assertTrue(" test".hasWhitespace())
    }

    @Test
    fun `hasWhitespace must return false`() {
        assertFalse("test".hasWhitespace())
    }

    @Test
    fun `toSpaceDelimitedString must return space delimited string`() {
        val actual = listOf("1", "2", "3").toSpaceDelimitedString()

        assertEquals("1 2 3", actual)
    }

    @Test
    fun `lengthiestString must return the lengthiest string`() {
        val actual = listOf("___", "_____", "__").lengthiestString()

        assertEquals("_____", actual)
    }

    @Test
    fun `lengthiestString must return null`() {
        val actual = listOf<String>().lengthiestString()

        assertNull(actual)
    }
}
