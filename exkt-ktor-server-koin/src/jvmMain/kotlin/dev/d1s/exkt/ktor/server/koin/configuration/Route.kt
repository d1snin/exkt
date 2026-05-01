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

package dev.d1s.exkt.ktor.server.koin.configuration

import io.ktor.server.routing.*
import org.koin.core.qualifier.Qualifier

/**
 * Used to define routes. Automatically loaded by [ServerApplication].
 */
public interface Route {

    /**
     * [Qualifier] for this Route.
     *
     * Example usage:
     * ```kotlin
     * // ...
     * override val qualifier = named("yet-another-route")
     * // ...
     * ```
     */
    public val qualifier: Qualifier

    public fun Routing.apply()
}