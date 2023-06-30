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

package dev.d1s.exkt.dto

import dev.d1s.exkt.dto.util.TestDtoConverter
import dev.d1s.exkt.dto.util.testDto
import dev.d1s.exkt.dto.util.testEntity
import io.mockk.coVerify
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import kotlin.test.*

class DtoConverterTest {

    private val testDtoConverter = spyk(TestDtoConverter())
    private val emptyDtoConverter = object : DtoConverter<Any, Any> {}

    private val testEntities = listOf(testEntity)
    private val testDtoList = listOf(testDto)

    @Test
    fun `convertToDto must throw NotImplementedError`() {
        assertThrowsNotImplementedError {
            runBlocking {
                emptyDtoConverter.convertToDto(Any())
            }

        }
    }

    @Test
    fun `convertToEntity must throw NotImplementedError`() {
        assertThrowsNotImplementedError {
            runBlocking {
                emptyDtoConverter.convertToEntity(Any())
            }
        }
    }

    @Test
    fun `must convert entity to dto with matching predicate`() {
        val actualDto = runBlocking {
            testDtoConverter.convertToDtoIf(testEntity) {
                true
            }
        }

        assertEquals(testDto, actualDto)

        coVerify {
            testDtoConverter.convertToDto(testEntity)
        }
    }

    @Test
    fun `must return null dto with mismatching predicate`() {
        val actualDto = runBlocking {
            testDtoConverter.convertToDtoIf(testEntity) {
                false
            }
        }

        assertNull(actualDto)

        coVerify(inverse = true) {
            testDtoConverter.convertToDto(testEntity)
        }
    }

    @Test
    fun `must convert entities to dto list`() {
        val actualDtoList = runBlocking {
            testDtoConverter.convertToDtoList(testEntities)
        }

        assertEquals(testDtoList, actualDtoList)

        coVerify {
            testDtoConverter.convertToDto(testEntity)
        }
    }

    @Test
    fun `must convert entities to dto list with matching predicate`() {
        val actualDtoList = runBlocking {
            testDtoConverter.convertToDtoListIf(testEntities) {
                true
            }
        }

        assertEquals(testDtoList, actualDtoList)

        coVerify {
            testDtoConverter.convertToDtoList(testEntities)
        }
    }

    @Test
    fun `must return null dto list with mismatching predicate`() {
        val actualDtoList = runBlocking {
            testDtoConverter.convertToDtoListIf(testEntities) {
                false
            }
        }

        assertNull(actualDtoList)

        coVerify(inverse = true) {
            testDtoConverter.convertToDtoList(testEntities)
        }
    }

    @Test
    fun `must convert dto list to entities`() {
        val actualEntities = runBlocking {
            testDtoConverter.convertToEntities(testDtoList)
        }

        assertEquals(testEntities, actualEntities)

        coVerify {
            testDtoConverter.convertToEntity(testDto)
        }
    }

    private inline fun assertThrowsNotImplementedError(block: () -> Unit) {
        val error = assertFails(block)

        assertIs<NotImplementedError>(error)
    }
}
