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
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DtoConverterTest {

    private val testDtoConverter = TestDtoConverter()

    private val testEntities = listOf(testEntity)
    private val testDtoList = listOf(testDto)

    @Test
    fun `must convert entity to dto`() {
        val actualDto = testDtoConverter.convertToDto(testEntity)

        assertEquals(testDto, actualDto)
    }

    @Test
    fun `must convert dto to entity`() {
        val actualEntity = testDtoConverter.convertToEntity(testDto)

        assertEquals(testEntity, actualEntity)
    }

    @Test
    fun `must convert entity to dto with matching predicate`() {
        val actualDto = testDtoConverter.convertToDtoIf(testEntity) {
            true
        }

        assertEquals(testDto, actualDto)
    }

    @Test
    fun `must return null dto with mismatching predicate`() {
        val actualDto = testDtoConverter.convertToDtoIf(testEntity) {
            false
        }

        assertNull(actualDto)
    }

    @Test
    fun `must convert entities to dto list`() {
        val actualDtoList = testDtoConverter.convertToDtoList(testEntities)

        assertEquals(testDtoList, actualDtoList)
    }

    @Test
    fun `must convert entities to dto list with matching predicate`() {
        val actualDtoList = testDtoConverter.convertToDtoListIf(testEntities) {
            true
        }

        assertEquals(testDtoList, actualDtoList)
    }

    @Test
    fun `must return null dto list with mismatching predicate`() {
        val actualDtoList = testDtoConverter.convertToDtoListIf(testEntities) {
            false
        }

        assertNull(actualDtoList)
    }

    @Test
    fun `must convert dto list to entities`() {
        val actualEntities = testDtoConverter.convertToEntities(testDtoList)

        assertEquals(testEntities, actualEntities)
    }
}
