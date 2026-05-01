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
import org.ktorm.schema.timestamp
import org.ktorm.schema.uuid
import java.time.Instant
import java.util.*

private const val DEFAULT_ID_COLUMN_NAME = "id"

private const val DEFAULT_CREATED_AT_COLUMN_NAME = "created_at"
private const val DEFAULT_UPDATED_AT_COLUMN_NAME = "updated_at"

internal fun <E : UuidIdentified<E>> Table<E>.idColumn(columnName: String? = null): Column<UUID> =
    uuid(columnName ?: DEFAULT_ID_COLUMN_NAME).primaryKey().bindTo {
        it.id
    }

internal fun <E : ModificationTimestampAware<E>> Table<E>.createdAtColumn(columnName: String? = null): Column<Instant> =
    timestamp(columnName ?: DEFAULT_CREATED_AT_COLUMN_NAME).bindTo {
        it.createdAt
    }

internal fun <E : ModificationTimestampAware<E>> Table<E>.updatedAtColumn(columnName: String? = null): Column<Instant> =
    timestamp(columnName ?: DEFAULT_UPDATED_AT_COLUMN_NAME).bindTo {
        it.updatedAt
    }

