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
import dev.d1s.exkt.dto.EntityWithOptionalDto
import dev.d1s.exkt.dto.convertToDtoList
import dev.d1s.exkt.ktorm.ExportedSequence
import org.ktorm.entity.Entity

/**
 * A pair of [ExportedSequence] represented as an entity and optional [ExportedSequenceDto] represented as a DTO.
 *
 * @see ExportedSequenceDto
 */
public typealias ExportedSequenceWithOptionalDto<TEntity, TDto> = EntityWithOptionalDto<ExportedSequence<TEntity>, ExportedSequenceDto<TDto>>

/**
 * A copy of [ExportedSequence] where elements are DTO.
 * You can utilize [convertExportedSequenceToDto] to convert [ExportedSequence] to [ExportedSequenceDto].
 *
 * @see ExportedSequenceWithOptionalDto
 * @see convertExportedSequenceToDto
 */
public data class ExportedSequenceDto<TDto : Any>(
    val limit: Int,
    val offset: Int,
    val totalCount: Int,
    val elements: List<TDto>
)

/**
 * Converts given [exportedSequence] to [ExportedSequenceDto].
 *
 * @see convertExportedSequenceToDtoIf
 */
public fun <TEntity : Entity<TEntity>, TDto : Any> DtoConverter<TEntity, TDto>.convertExportedSequenceToDto(
    exportedSequence: ExportedSequence<TEntity>
): ExportedSequenceDto<TDto> =
    with(exportedSequence) {
        val dtoList = convertToDtoList(elements)

        ExportedSequenceDto(limit, offset, totalCount, dtoList)
    }

/**
 * Converts given [exportedSequence] to [ExportedSequenceDto] if the given [predicate] matches. Returns `null` otherwise.
 */
public fun <TEntity : Entity<TEntity>, TDto : Any> DtoConverter<TEntity, TDto>.convertExportedSequenceToDtoIf(
    exportedSequence: ExportedSequence<TEntity>,
    predicate: () -> Boolean
): ExportedSequenceDto<TDto>? =
    if (predicate()) {
        convertExportedSequenceToDto(exportedSequence)
    } else {
        null
    }