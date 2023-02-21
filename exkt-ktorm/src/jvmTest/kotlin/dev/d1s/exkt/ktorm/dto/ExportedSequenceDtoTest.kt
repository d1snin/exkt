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

package dev.d1s.exkt.ktorm.dto

import dev.d1s.exkt.dto.DtoConverter
import dev.d1s.exkt.ktorm.ExportedSequence
import dev.d1s.exkt.ktorm.util.*
import io.mockk.spyk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ExportedSequenceDtoTest {

    private val testElement = testEntity

    private val testExportedSequence = ExportedSequence(
        limit = MOCK_LIMIT,
        offset = MOCK_OFFSET,
        totalCount = MOCK_COUNT,
        elements = listOf(testElement)
    )

    private val testDto = Any()

    private val testDtoConverter = spyk(TestEntityDtoConverter())

    private val expectedExportedSequenceDto = ExportedSequenceDto(
        limit = MOCK_LIMIT,
        offset = MOCK_OFFSET,
        totalCount = MOCK_COUNT,
        listOf(testDto)
    )

    @Test
    fun `must convert exported sequence to dto`() {
        val actualExportedSequenceDto = testDtoConverter.convertExportedSequenceToDto(testExportedSequence)

        assertEquals(expectedExportedSequenceDto, actualExportedSequenceDto)

        verify {
            testDtoConverter.convertToDto(testElement)
        }
    }

    @Test
    fun `must convert exported sequence to dto with matching predicate`() {
        val actualExportedSequenceDto = testDtoConverter.convertExportedSequenceToDtoIf(testExportedSequence) {
            true
        }

        assertEquals(expectedExportedSequenceDto, actualExportedSequenceDto)

        verify {
            testDtoConverter.convertExportedSequenceToDto(testExportedSequence)
        }
    }

    @Test
    fun `must convert exported sequence to dto with mismatching predicate`() {
        val actualExportedSequenceDto = testDtoConverter.convertExportedSequenceToDtoIf(testExportedSequence) {
            false
        }

        assertNull(actualExportedSequenceDto)

        verify(inverse = true) {
            testDtoConverter.convertExportedSequenceToDto(testExportedSequence)
        }
    }

    inner class TestEntityDtoConverter : DtoConverter<TestEntity, Any> {

        override fun convertToDto(entity: TestEntity): Any = testDto
    }
}