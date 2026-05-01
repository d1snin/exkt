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

package dev.d1s.exkt.postgres

import org.postgresql.util.PSQLException
import org.postgresql.util.PSQLState

public inline fun <R> handlePsqlException(psqlState: PSQLState, block: () -> R): Result<R> = runCatching {
    block()
}.onFailure { error ->
    val psqlException = error as? PSQLException
    val errorMessage = psqlException?.serverErrorMessage
    val sqlState = errorMessage?.sqlState

    if (sqlState != psqlState.state) {
        throw error
    }
}

public inline fun <R> handlePsqlExceptionOrThrow(psqlState: PSQLState, exception: () -> Throwable, block: () -> R): R =
    handlePsqlException(psqlState, block).getOrElse {
        throw exception()
    }

public inline fun <R> handlePsqlUniqueViolation(block: () -> R): Result<R> =
    handlePsqlException(PSQLState.UNIQUE_VIOLATION, block)
