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

package dev.d1s.exkt.ktor.server.postgres

import dev.d1s.exkt.postgres.handlePsqlUniqueViolation
import io.ktor.server.plugins.*

public inline fun <R> handlePsqlUniqueViolationOrThrowNotFoundException(message: String? = null, block: () -> R): R =
    handlePsqlUniqueViolation(block).getOrElse {
        // if message is not null - use it
        // if message is null - use NotFoundException's default one
        message?.let {
            throw NotFoundException(it)
        } ?: throw NotFoundException()
    }