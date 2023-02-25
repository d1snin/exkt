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

package dev.d1s.exkt.ktor.server.koin.configuration

import dev.d1s.exkt.common.withEach
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.lighthousegames.logging.logging

internal interface RouteInstaller {

    fun Routing.installRoutes()
}

internal class DefaultRouteInstaller : RouteInstaller, KoinComponent {

    private val routes by lazy {
        getKoin().getAll<Route>()
    }

    private val logger = logging()

    override fun Routing.installRoutes() {
        logger.d {
            "Installing routes..."
        }

        routes.withEach {
            apply()
        }
    }
}