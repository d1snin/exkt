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

package dev.d1s.exkt.ktor.server

import io.ktor.server.config.*

private const val DATABASE_URL_PROPERTY = "database.url"
private const val DATABASE_USER_PROPERTY = "database.user"
private const val DATABASE_PASSWORD_PROPERTY = "database.password"

public data class JdbcProperties(
    val url: String,
    val user: String,
    val password: String
)

/**
 * A shortcut for [retrieveJdbcProperties].
 *
 * @see retrieveJdbcProperties
 */
public val ApplicationConfig.jdbcProperties: JdbcProperties? get() = retrieveJdbcProperties()

/**
 * A shortcut for [retrieveJdbcPropertiesOrThrow].
 *
 * @see retrieveJdbcPropertiesOrThrow
 */
public val ApplicationConfig.requiredJdbcProperties: JdbcProperties get() = retrieveJdbcPropertiesOrThrow()

/**
 * Tries to retrieve [JDBC properties][JdbcProperties] from the [application configuration][ApplicationConfig].
 * If any of the properties isn't present, `null` is returned, a complete [JdbcProperties] object otherwise.
 * The following properties are fetched:
 * - `database.url`
 * - `database.user`
 * - `database.password`
 *
 * @see jdbcProperties
 */
public fun ApplicationConfig.retrieveJdbcProperties(): JdbcProperties? {
    val databaseUrl = stringProperty(DATABASE_URL_PROPERTY) ?: return null
    val databaseUser = stringProperty(DATABASE_USER_PROPERTY) ?: return null
    val databasePassword = stringProperty(DATABASE_PASSWORD_PROPERTY) ?: return null

    return JdbcProperties(databaseUrl, databaseUser, databasePassword)
}

/**
 * Calls [retrieveJdbcProperties] and fails if the returned properties are `null`.
 *
 * @see retrieveJdbcProperties
 */
public fun ApplicationConfig.retrieveJdbcPropertiesOrThrow(): JdbcProperties =
    retrieveJdbcProperties()
        ?: error("A complete set of JDBC properties is expected in the application config: $DATABASE_URL_PROPERTY, $DATABASE_USER_PROPERTY, $DATABASE_PASSWORD_PROPERTY")

private fun ApplicationConfig.stringProperty(name: String) = propertyOrNull(name)?.getString()