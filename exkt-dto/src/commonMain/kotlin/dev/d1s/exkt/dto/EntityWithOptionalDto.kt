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

public typealias EntityWithOptionalDto<E, D> = Pair<E, D?>
public typealias EntityWithOptionalDtoList<E, D> = Pair<List<E>, List<D>?>

public typealias ResultingEntityWithOptionalDto<E, D> = Result<EntityWithOptionalDto<E, D>>
public typealias ResultingEntityWithOptionalDtoList<E, D> = Result<EntityWithOptionalDtoList<E, D>>

public val <E, D> EntityWithOptionalDto<E, D>.entity: E
    get() = first

public val <E, D> EntityWithOptionalDto<E, D>.dto: D?
    get() = second

public val <E, D> EntityWithOptionalDtoList<E, D>.entities: List<E>
    get() = first

public val <E, D> EntityWithOptionalDtoList<E, D>.dtoList: List<D>?
    get() = second

public val <E, D> EntityWithOptionalDto<E, D>.requiredDto: D
    get() = requireNotNull(dto)

public val <E, D> EntityWithOptionalDtoList<E, D>.requiredDtoList: List<D>
    get() = requireNotNull(dtoList)
