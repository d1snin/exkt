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
     * @see convertToEntities
     */
    public fun convertToEntity(dto: TDto): TEntity {
        throw NotImplementedError()
    }
}

/**
 * Converts given [entity] to [DTO][TDto] if the given [predicate] matches. Returns `null` otherwise.
 */
public fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.convertToDtoIf(entity: TEntity, predicate: () -> Boolean): TDto? =
    if (predicate()) {
        this.convertToDto(entity)
    } else {
        null
    }

/**
 * Converts given [entities] to [DTO list][List].
 */
public fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.convertToDtoList(entities: List<TEntity>): List<TDto> =
    entities.map {
        this.convertToDto(it)
    }

/**
 * Converts given [entities] to [DTO list][List] if the given [predicate] matches. Returns `null` otherwise.
 */
public fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.convertToDtoListIf(entities: List<TEntity>, predicate: () -> Boolean): List<TDto>? =
    if (predicate()) {
        this.convertToDtoList(entities)
    } else {
        null
    }

/**
 * Converts given [DTO list][dtoList] to [entities][List].
 */
public fun <TEntity : Any, TDto : Any> DtoConverter<TEntity, TDto>.convertToEntities(dtoList: List<TDto>): List<TEntity> =
    dtoList.map {
        this.convertToEntity(it)
    }
