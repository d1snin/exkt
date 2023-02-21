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

import dev.d1s.exkt.ktorm.util.testModificationTimestampAwareEntity
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import kotlin.test.Test
import java.time.Instant
import kotlin.test.assertEquals

class ModificationTimestampAwareTest {

    private val modificationTimestampAware = testModificationTimestampAwareEntity

    private val mockInstant = Instant.EPOCH

    @Test
    fun `must set valid creation timestamp`() {
        withMockedInstantNow {
            modificationTimestampAware.setCreatedAt()

            assertEquals(mockInstant, modificationTimestampAware.createdAt)

            verifyCall()
        }
    }

    @Test
    fun `must set valid update timestamp`() {
        withMockedInstantNow {
            modificationTimestampAware.setUpdatedAt()

            assertEquals(mockInstant, modificationTimestampAware.updatedAt)

            verifyCall()
        }
    }

    private fun verifyCall() {
        verify {
            Instant.now()
        }
    }

    private inline fun withMockedInstantNow(crossinline block: () -> Unit) {
        mockkStatic(Instant::class) {
            every {
                Instant.now()
            } returns mockInstant

            block()
        }
    }
}