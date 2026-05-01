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

import org.ktorm.schema.Column
import org.ktorm.schema.Table
import java.util.*

public abstract class UuidIdentifiedEntities<E : UuidIdentified<E>>(
    tableName: String,
    idColumnName: String? = null
) : Table<E>(tableName), UuidIdentifiedEntitiesBase {

    public override val id: Column<UUID> = idColumn(idColumnName)
}