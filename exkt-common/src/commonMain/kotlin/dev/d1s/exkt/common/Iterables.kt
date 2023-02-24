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

package dev.d1s.exkt.common

/**
 * Returns `true` if one of the elements in this collection is in [the other one][other]. `false` otherwise.
 */
public infix fun <T> Iterable<T>.anyIn(other: Iterable<T>): Boolean {
    forEach {
        if (it in other) {
            return true
        }
    }

    return false
}

/**
 * Transforms the elements of this collection using [map] and returns [MutableList] representation of the transformed collection.
 */
public inline fun <E, R> Iterable<E>.mapToMutableList(transform: (E) -> R): MutableList<R> =
    this.map(transform).toMutableList()

/**
 * Returns `true` if this collection has duplicate objects. `false` otherwise.
 *
 * @see hasDuplicatesOf
 */
public fun Iterable<*>.hasDuplicates(): Boolean = this.toSet().size != this.toList().size

/**
 * Returns `true` if this collection has duplicate objects produced by the given [selector] function. `false` otherwise.
 */
public fun <E, T> Iterable<E>.hasDuplicatesOf(selector: (E) -> T): Boolean = this.map {
    selector(it)
}.hasDuplicates()