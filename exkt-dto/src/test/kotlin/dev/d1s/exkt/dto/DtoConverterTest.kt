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
import io.mockk.spyk
import io.mockk.verify
import kotlin.test.*

class DtoConverterTest {

    private val testDtoConverter = spyk(TestDtoConverter())
    private val emptyDtoConverter = object : DtoConverter<Any, Any> {}

    private val testEntities = listOf(testEntity)
    private val testDtoList = listOf(testDto)

    @Test
    fun `convertToDto must throw NotImplementedError`() {
        assertThrowsNotImplementedError {
            emptyDtoConverter.convertToDto(Any())
        }
    }

    @Test
    fun `convertToEntity must throw NotImplementedError`() {
        assertThrowsNotImplementedError {
            emptyDtoConverter.convertToEntity(Any())
        }
    }

    @Test
    fun `must convert entity to dto with matching predicate`() {
        val actualDto = testDtoConverter.convertToDtoIf(testEntity) {
            true
        }

        assertEquals(testDto, actualDto)

        verify {
            testDtoConverter.convertToDto(testEntity)
        }
    }

    @Test
    fun `must return null dto with mismatching predicate`() {
        val actualDto = testDtoConverter.convertToDtoIf(testEntity) {
            false
        }

        assertNull(actualDto)

        verify(inverse = true) {
            testDtoConverter.convertToDto(testEntity)
        }
    }

    @Test
    fun `must convert entities to dto list`() {
        val actualDtoList = testDtoConverter.convertToDtoList(testEntities)

        assertEquals(testDtoList, actualDtoList)

        verify {
            testDtoConverter.convertToDto(testEntity)
        }
    }

    @Test
    fun `must convert entities to dto list with matching predicate`() {
        val actualDtoList = testDtoConverter.convertToDtoListIf(testEntities) {
            true
        }

        assertEquals(testDtoList, actualDtoList)

        verify {
            testDtoConverter.convertToDtoList(testEntities)
        }
    }

    @Test
    fun `must return null dto list with mismatching predicate`() {
        val actualDtoList = testDtoConverter.convertToDtoListIf(testEntities) {
            false
        }

        assertNull(actualDtoList)

        verify(inverse = true) {
            testDtoConverter.convertToDtoList(testEntities)
        }
    }

    @Test
    fun `must convert dto list to entities`() {
        val actualEntities = testDtoConverter.convertToEntities(testDtoList)

        assertEquals(testEntities, actualEntities)

        verify {
            testDtoConverter.convertToEntity(testDto)
        }
    }

    private inline fun assertThrowsNotImplementedError(block: () -> Unit) {
        val error = assertFails(block)

        assertIs<NotImplementedError>(error)
    }
}
