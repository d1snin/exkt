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

import kotlin.test.Test
import kotlin.test.assertEquals

class IntRangesTest {

    @Test
    fun `must return valid int range`() {
        assertParsedIntRangeEquals(-324..5_252_626, "-324..5252626")
    }

    @Test
    fun `must return null`() {
        assertParsedIntRangeEquals(null, "-3..5..5")
        assertParsedIntRangeEquals(null, "-3..5..")
        assertParsedIntRangeEquals(null, "-3....5")
        assertParsedIntRangeEquals(null, "-3..")
        assertParsedIntRangeEquals(null, "47...6")
        assertParsedIntRangeEquals(null, "123..${Long.MAX_VALUE}")
        assertParsedIntRangeEquals(null, "-6246..4_342_532")
    }

    private fun assertParsedIntRangeEquals(expected: IntRange?, declaration: String) {
        val parsedIntRange = declaration.parseIntRange()
        assertEquals(expected, parsedIntRange)
    }
}