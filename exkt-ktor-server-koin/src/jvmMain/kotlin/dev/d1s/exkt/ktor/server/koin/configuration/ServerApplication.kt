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

package dev.d1s.exkt.ktor.server.koin.configuration

import com.typesafe.config.ConfigFactory
import dev.d1s.exkt.common.withEach
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.Module
import kotlin.getValue
import kotlin.with
import org.koin.dsl.module as koinModule

public typealias Configurers = List<Configurer<*>>

/**
 * [ServerApplication] class provides quick extensions for configuring Ktor Server at startup.
 *
 * Example usage:
 * ```kotlin
 * class MyApplication : ServerApplication() {
 *
 *     override val configurers = listOf(Routing, Security)
 *
 *     override fun launch() {
 *         val environment = createApplicationEngineEnvironment()
 *
 *         embeddedServer(Netty, environment).start(wait = true)
 *     }
 * }
 * ```
 *
 * @see Configurer
 */
public abstract class ServerApplication : KoinComponent {

    public abstract val configurers: Configurers

    private val environmentConfigurers get() = configurers.filterIsInstance<EnvironmentConfigurer>()
    private val applicationConfigurers get() = configurers.filterIsInstance<ApplicationConfigurer>()

    private val routeInstaller by inject<RouteInstaller>()

    public abstract fun launch()

    public fun createApplicationEngineEnvironment(
        koinModule: Module = koinModule {},
        config: ApplicationConfig = makeHoconApplicationConfig()
    ): ApplicationEngineEnvironment = applicationEngineEnvironment {
        this.applyEnvironmentConfiguration(koinModule, config)
    }

    public fun ApplicationEngineEnvironmentBuilder.applyEnvironmentConfiguration(
        koinModule: Module = koinModule {},
        config: ApplicationConfig = makeHoconApplicationConfig()
    ) {
        this.config = config

        this.applyEnvironmentConfigurers(koinModule, config)

        module {
            applyApplicationConfigurersAndRoutes(koinModule, config)
        }
    }

    public fun Application.applyApplicationConfigurersAndRoutes(
        koinModule: Module = koinModule {},
        config: ApplicationConfig = environment.config
    ) {
        applicationConfigurers.withEach {
            configure(koinModule, config)
        }

        installRoutes()
    }

    private fun ApplicationEngineEnvironmentBuilder.applyEnvironmentConfigurers(
        koinModule: Module,
        config: ApplicationConfig
    ) {
        environmentConfigurers.withEach {
            configure(koinModule, config)
        }
    }

    private fun Application.installRoutes() {
        routing {
            with(routeInstaller) {
                installRoutes()
            }
        }
    }

    private fun makeHoconApplicationConfig(): ApplicationConfig {
        val loadedHoconConfig = ConfigFactory.load()

        return HoconApplicationConfig(loadedHoconConfig)
    }
}