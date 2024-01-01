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

package dev.d1s.exkt.test

import kotlinx.coroutines.runBlocking
import kotlin.test.assertFails
import kotlin.test.assertFailsWith

public inline fun assertFails(crossinline block: suspend () -> Unit): Throwable =
    runBlocking {
        assertFails {
            block()
        }
    }

public inline fun <reified T : Throwable> assertFailsWith(crossinline block: suspend () -> Unit): Throwable =
    runBlocking {
        assertFailsWith<T> {
            block()
        }
    }