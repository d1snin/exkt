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

import org.ktorm.entity.*
import org.ktorm.expression.OrderByExpression
import org.ktorm.schema.Table

private const val DEFAULT_LIMIT: Int = 1000
private const val DEFAULT_OFFSET: Int = 0

/**
 * Exported [EntitySequence].
 *
 * @param limit Max amount of elements in this exported sequence.
 * @param offset The amount of elements omitted from the initial sequence at the start.
 * @param totalCount Total amount of elements in the initial sequence.
 *
 * @see export
 */
public data class ExportedSequence<E : Entity<E>>(
    val limit: Int,
    val offset: Int,
    val totalCount: Int,
    val elements: List<E>
)

/**
 * Exports an [EntitySequence].
 *
 * @param limit Max amount of elements to include into the exported sequence.
 * @param offset The amount of elements to omit at the start.
 *
 * @see ExportedSequence
 */
public fun <E : Entity<E>, T : Table<E>> EntitySequence<E, T>.export(
    limit: Int = DEFAULT_LIMIT,
    offset: Int = DEFAULT_OFFSET,
    sort: ((T) -> OrderByExpression)? = null
): ExportedSequence<E> {
    val totalCount = count()
    val trimmedElements = drop(offset).take(limit)
    val sortedElements = sort?.let { selector ->
        sortedBy(selector)
    } ?: trimmedElements
    val elements = sortedElements.toMutableList()

    if (limit == 0 || offset >= totalCount) {
        elements.clear()
    }

    return ExportedSequence(limit, offset, totalCount, elements)
}
