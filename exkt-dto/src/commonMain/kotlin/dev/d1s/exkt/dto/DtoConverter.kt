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

package dev.d1s.exkt.dto

/**
 * A converter supposed to convert [Business Objects](https://en.wikipedia.org/wiki/Business_object)
 * to [Data Transfer Objects](https://en.wikipedia.org/wiki/Data_transfer_object) and vice versa.
 *
 * Implementers have to implement at least either [convertToDto] or [convertToEntity].
 *
 * There are extension functions available for this interface:
 * - [convertToDtoIf]
 * - [convertToDtoList]
 * - [convertToDtoListIf]
 * - [convertToEntities]
 */
public interface DtoConverter<TEntity : Any, TDto : Any> {

    /**
     * Converts given [entity] to [DTO][TDto].
     *
     * @see prepareAndConvertToDto
     * @see convertToDtoIf
     * @see convertToDtoList
     * @see convertToDtoListIf
     */
    public fun convertToDto(entity: TEntity): TDto {
        throw NotImplementedError()
    }

    /**
     * Converts given [DTO][dto] to [entity][TEntity].
     *
     * @see prepareAndConvertToEntity
     * @see convertToEntities
     */
    public fun convertToEntity(dto: TDto): TEntity {
        throw NotImplementedError()
    }

    /**
     * Prepares this instance to convert given [entity] to [DTO][TDto].
     */
    public suspend fun prepareConversionToDto(entity: TEntity) {
        throw NotImplementedError()
    }

    /**
     * Prepares this instance to convert given [DTO][dto] to [entity][TEntity].
     */
    public suspend fun prepareConversionToEntity(dto: TDto) {
        throw NotImplementedError()
    }
}

/**
 * Throws [IllegalStateException] indicating that this operation can only be called after preparation.
 */
public fun errorNotPrepared(): Nothing = throw IllegalStateException("This operation requires preparation.")

/**
 * Converts given [entity] to [DTO][TDto] after preparation.
 */
public suspend fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.prepareAndConvertToDto(entity: TEntity): TDto {
    try {
        prepareConversionToDto(entity)
    } catch (_: NotImplementedError) {
    }

    return convertToDto(entity)
}

/**
 * Converts given [DTO][dto] to [entity][TEntity] after preparation.
 */
public suspend fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.prepareAndConvertToEntity(dto: TDto): TEntity {
    try {
        prepareConversionToEntity(dto)
    } catch (_: NotImplementedError) {
    }

    return convertToEntity(dto)
}

/**
 * Converts given [entity] to [DTO][TDto] if the given [predicate] matches. Returns `null` otherwise.
 */
public fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.convertToDtoIf(
    entity: TEntity,
    predicate: () -> Boolean
): TDto? =
    if (predicate()) {
        convertToDto(entity)
    } else {
        null
    }

/**
 * Converts given [entity] to [DTO][TDto] after preparation if the given [predicate] matches. Returns `null` otherwise.
 */
public suspend fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.prepareAndConvertToDtoIf(
    entity: TEntity,
    predicate: () -> Boolean
): TDto? =
    if (predicate()) {
        prepareAndConvertToDto(entity)
    } else {
        null
    }

/**
 * Converts given [entities] to [DTO list][List].
 */
public fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.convertToDtoList(entities: List<TEntity>): List<TDto> =
    entities.map {
        convertToDto(it)
    }

/**
 * Converts given [entities] to [DTO list][List]. Prepares before each conversion.
 */
public suspend fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.prepareAndConvertToDtoList(entities: List<TEntity>): List<TDto> =
    entities.map {
        prepareAndConvertToDto(it)
    }

/**
 * Converts given [entities] to [DTO list][List] if the given [predicate] matches. Returns `null` otherwise.
 */
public fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.convertToDtoListIf(
    entities: List<TEntity>,
    predicate: () -> Boolean
): List<TDto>? =
    if (predicate()) {
        convertToDtoList(entities)
    } else {
        null
    }

/**
 * Converts given [entities] to [DTO list][List] if the given [predicate] matches. Prepares before each conversion.
 * Returns `null` otherwise.
 */
public suspend fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.prepareAndConvertToDtoListIf(
    entities: List<TEntity>,
    predicate: () -> Boolean
): List<TDto>? =
    if (predicate()) {
        prepareAndConvertToDtoList(entities)
    } else {
        null
    }

/**
 * Converts given [DTO list][dtoList] to [entities][List].
 */
public fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.convertToEntities(dtoList: List<TDto>): List<TEntity> =
    dtoList.map {
        convertToEntity(it)
    }

/**
 * Converts given [DTO list][dtoList] to [entities][List]. Prepares before each conversion.
 */
public suspend fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.prepareAndConvertToEntities(dtoList: List<TDto>): List<TEntity> =
    dtoList.map {
        prepareAndConvertToEntity(it)
    }
