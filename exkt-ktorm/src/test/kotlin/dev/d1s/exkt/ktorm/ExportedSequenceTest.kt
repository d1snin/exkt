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

package dev.d1s.exkt.ktorm

import dev.d1s.exkt.ktorm.util.TestEntity
import dev.d1s.exkt.ktorm.util.testEntityInstance
import dev.d1s.exkt.ktorm.util.mockStaticEntitySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlin.test.Test
import org.ktorm.entity.*
import org.ktorm.schema.Table
import kotlin.test.assertEquals

private typealias EntitySequenceMock = EntitySequence<TestEntity, Table<TestEntity>>

class ExportedSequenceTest {

    private val entitySequenceMock = mockk<EntitySequenceMock>()

    private val mockElements = listOf(testEntityInstance)

    @Test
    fun `must export entity sequence`() {
        withMockedExtensionFunctions {
            val exportedSequence = entitySequenceMock.export(limit = MOCK_LIMIT, offset = MOCK_OFFSET)

            with(exportedSequence) {
                assertEquals(MOCK_LIMIT, limit)
                assertEquals(MOCK_OFFSET, offset)
                assertEquals(MOCK_COUNT, totalCount)
                assertEquals(mockElements, elements)
            }

            verifyOrder {
                with(entitySequenceMock) {
                    count()
                    drop(MOCK_OFFSET)
                    take(MOCK_LIMIT)
                    toList()
                }
            }
        }
    }

    private inline fun withMockedExtensionFunctions(block: () -> Unit) =
        mockStaticEntitySequence {
            every {
                entitySequenceMock.count()
            } returns MOCK_COUNT

            every {
                entitySequenceMock.drop(MOCK_OFFSET)
            } returns entitySequenceMock

            every {
                entitySequenceMock.take(MOCK_LIMIT)
            } returns entitySequenceMock

            every {
                entitySequenceMock.toList()
            } returns mockElements

            block()
        }

    private companion object {

        private const val MOCK_LIMIT = 1500
        private const val MOCK_OFFSET = 200

        private const val MOCK_COUNT = 2000
    }
}