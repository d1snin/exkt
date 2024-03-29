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
public fun <E, T> EntitySequence<E, T>.export(
    limit: Int = DEFAULT_LIMIT,
    offset: Int = DEFAULT_OFFSET,
    sort: ((T) -> OrderByExpression)? = null,
    clientTransform: (List<E>.() -> List<E>)? = null
): ExportedSequence<E> where E : Entity<E>, T : Table<E> {
    val totalCount = count()

    val sortedElements = sort?.let { selector ->
        sortedBy(selector)
    } ?: this

    var processedElements: List<E>? = null

    clientTransform?.let { process ->
        processedElements = sortedElements.toList().process()
    }

    val limitedElements = processedElements?.take(limit) ?: sortedElements.take(limit).toList()

    val elementsWithoutOffset = limitedElements.drop(offset)

    val elements = elementsWithoutOffset.toMutableList()

    if (limit == 0) {
        elements.clear()
    }

    return ExportedSequence(limit, offset, totalCount, elements)
}
