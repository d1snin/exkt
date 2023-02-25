/*
 *  Copyright 2023 VTITBiD.TECH Research Group <info@vtitbid.tech>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package dev.d1s.exkt.ktor.server.koin.configuration.builtin

import dev.d1s.exkt.ktor.server.koin.configuration.DefaultRouteInstaller
import dev.d1s.exkt.ktor.server.koin.configuration.Route
import dev.d1s.exkt.ktor.server.koin.configuration.RouteInstaller
import org.koin.core.module.Module

public class Routes internal constructor() : MutableList<Route> by mutableListOf() {

    public operator fun Route.unaryPlus(): Boolean = add(this)
}

/**
 * Supposed to be used in [Configurers][dev.d1s.exkt.ktor.server.koin.configuration.Configurer] to configure routes.
 */
public fun Module.configureRoutes(builder: Routes.() -> Unit) {
    val routes = Routes().apply(builder)

    routes.forEach { route ->
        single {
            route
        }
    }

    single<RouteInstaller> {
        DefaultRouteInstaller()
    }
}