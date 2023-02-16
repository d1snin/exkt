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

package dev.d1s.exkt.ktor.server

import io.ktor.server.config.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

class JdbcPropertiesTest {

    private val applicationConfigMock = makeApplicationConfigMock()

    private val expectedProperties = JdbcProperties(MOCK_URL, MOCK_USER, MOCK_PASSWORD)

    @Test
    fun `retrieveJdbcProperties must return valid jdbc properties`() {
        val actualProperties = applicationConfigMock.retrieveJdbcProperties()

        verifyProperties(actualProperties)
    }

    @Test
    fun `retrieveJdbcProperties must return null`() {
        makeRandomPropertyNull()

        val actualProperties = applicationConfigMock.retrieveJdbcProperties()

        assertNull(actualProperties)
    }

    @Test
    fun `retrieveJdbcPropertiesOrThrow must throw an error`() {
        makeRandomPropertyNull()

        assertFails {
            applicationConfigMock.retrieveJdbcPropertiesOrThrow()
        }
    }

    @Test
    fun `jdbcProperties extension must return valid jdbc properties`() {
        val actualProperties = applicationConfigMock.jdbcProperties

        verifyProperties(actualProperties)
    }

    @Test
    fun `jdbcProperties extension must return null`() {
        makeRandomPropertyNull()

        val actualProperties = applicationConfigMock.jdbcProperties

        assertNull(actualProperties)
    }

    @Test
    fun `requiredJdbcProperties extension must throw an error`() {
        makeRandomPropertyNull()

        assertFails {
            applicationConfigMock.requiredJdbcProperties
        }
    }

    private fun makeApplicationConfigMock() = mockk<ApplicationConfig> {
        every {
            propertyOrNull(DATABASE_URL_PROPERTY)?.getString()
        } returns MOCK_URL

        every {
            propertyOrNull(DATABASE_USER_PROPERTY)?.getString()
        } returns MOCK_USER

        every {
            propertyOrNull(DATABASE_PASSWORD_PROPERTY)?.getString()
        } returns MOCK_PASSWORD
    }

    private fun verifyProperties(actualProperties: JdbcProperties?) {
        assertNotNull(actualProperties)
        assertEquals(expectedProperties, actualProperties)

        verifyAllCalls()
    }

    private fun verifyAllCalls() {
        verify {
            applicationConfigMock.propertyOrNull(DATABASE_URL_PROPERTY)?.getString()
            applicationConfigMock.propertyOrNull(DATABASE_USER_PROPERTY)?.getString()
            applicationConfigMock.propertyOrNull(DATABASE_PASSWORD_PROPERTY)?.getString()
        }
    }

    private fun makeRandomPropertyNull() {
        every {
            when ((1..3).random()) {
                1 -> applicationConfigMock.propertyOrNull(DATABASE_URL_PROPERTY)?.getString()
                2 -> applicationConfigMock.propertyOrNull(DATABASE_URL_PROPERTY)?.getString()
                3 -> applicationConfigMock.propertyOrNull(DATABASE_URL_PROPERTY)?.getString()
                else -> error("(1..3).random() returned something else, not 1, 2 or 3")
            }
        } returns null
    }

    private companion object {

        private const val DATABASE_URL_PROPERTY = "database.url"
        private const val DATABASE_USER_PROPERTY = "database.user"
        private const val DATABASE_PASSWORD_PROPERTY = "database.password"

        private const val MOCK_URL = "test_url"
        private const val MOCK_USER = "test_user"
        private const val MOCK_PASSWORD = "test_password"
    }
}