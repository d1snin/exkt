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

import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IterablesTest {

    @Test
    fun `anyIn must return true`() {
        val actual = listOf(1, 2, 3) anyIn listOf(3, 4, 5)
        assertTrue(actual)
    }

    @Test
    fun `anyIn must return false`() {
        val actual = listOf(1, 2, 3) anyIn listOf(4, 5, 6)
        assertFalse(actual)
    }

    @Test
    fun `mapToMutableList must transform elements and return MutableList`() {
        val actual = listOf(1, 2, 3).mapToMutableList {
            it.toString()
        }

        assertEquals(mutableListOf("1", "2", "3"), actual)
    }

    @Test
    fun `hasDuplicates must return true`() {
        val actual = listOf(1, 1, 2, 3).hasDuplicates()

        assertTrue(actual)
    }

    @Test
    fun `hasDuplicates must return false`() {
        val actual = listOf(1, 2, 3, 4).hasDuplicates()

        assertFalse(actual)
    }

    @Test
    fun `hasDuplicatesOf must return true`() {
        val actual = listOf(1, 2, 3, 3).hasDuplicatesOf {
            it.toString()
        }

        assertTrue(actual)
    }

    @Test
    fun `hasDuplicatesOf must return false`() {
        val actual = listOf(1, 2, 3, 4).hasDuplicatesOf {
            it.toString()
        }

        assertFalse(actual)
    }

    @Test
    fun `withEach must call lambda on each element`() {
        val receiver = TestReceiver.mock

        listOf(receiver).withEach {
            doSomething()
        }

        verify {
            receiver.doSomething()
        }
    }
}

private interface TestReceiver {

    fun doSomething()

    companion object {

        val mock get() = mockk<TestReceiver>(relaxUnitFun = true)
    }
}