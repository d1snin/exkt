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

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EntityWithOptionalDtoTest {

    private val entity = Any()
    private val dto = Any()

    private val entities = listOf(Any())
    private val dtoList = listOf(Any())

    private val entityWithOptionalDto: EntityWithOptionalDto<Any, Any> = entity to dto
    private val entityWithOptionalDtoList: EntityWithOptionalDtoList<Any, Any> = entities to dtoList

    @Test
    fun `entity extension must return first value of the pair`() {
        assertEquals(entity, entityWithOptionalDto.entity)
    }

    @Test
    fun `dto extension must return second value of the pair`() {
        assertEquals(dto, entityWithOptionalDto.dto)
    }

    @Test
    fun `entities extension must return first value of the pair`() {
        assertEquals(entities, entityWithOptionalDtoList.entities)
    }

    @Test
    fun `dtoList extension must return second value of the pair`() {
        assertEquals(dtoList, entityWithOptionalDtoList.dtoList)
    }

    @Test
    fun `requiredDto extension must return not null dto`() {
        assertEquals(dto, entityWithOptionalDto.requiredDto)
    }

    @Test
    fun `requiredDto extension must throw IllegalArgumentException`() {
        val entityWithNullDto: EntityWithOptionalDto<Any, Any> = entity to null

        assertFailsWith<IllegalArgumentException> {
            entityWithNullDto.requiredDto
        }
    }

    @Test
    fun `requiredDtoList extension must throw IllegalArgumentException`() {
        val entityWithNullDtoList: EntityWithOptionalDtoList<Any, Any> = entities to null

        assertFailsWith<IllegalArgumentException> {
            entityWithNullDtoList.requiredDtoList
        }
    }
}