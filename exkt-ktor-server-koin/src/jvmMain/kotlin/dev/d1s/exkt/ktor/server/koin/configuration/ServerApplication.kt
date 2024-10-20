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

import com.typesafe.config.ConfigFactory
import dev.d1s.exkt.common.withEach
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import org.koin.core.component.KoinComponent
import org.koin.core.error.NoDefinitionFoundException
import org.koin.core.module.Module
import kotlin.getValue
import kotlin.lazy
import org.koin.dsl.module as koinModule

public typealias Configurers = List<Configurer<*>>

/**
 * [ServerApplication] class provides quick extensions for configuring Ktor Server at startup.
 *
 * @see Configurer
 */
public abstract class ServerApplication : KoinComponent {

    public abstract val configurers: Configurers

    public val koinModule: Module by lazy {
        koinModule { }
    }

    public val config: ApplicationConfig by lazy {
        makeHoconApplicationConfig()
    }

    private val engineConfigurers get() = configurers.filterIsInstance<EngineConfigurer>()
    private val environmentConfigurers get() = configurers.filterIsInstance<EnvironmentConfigurer>()
    private val applicationConfigurers get() = configurers.filterIsInstance<ApplicationConfigurer>()

    public abstract fun launch()

    public fun createApplicationEngineEnvironment(
        koinModule: Module = this.koinModule,
        config: ApplicationConfig = this.config,
    ): ApplicationEnvironment = applicationEnvironment {
        this.applyEnvironmentConfiguration(koinModule, config)
    }

    public fun BaseApplicationEngine.Configuration.applyEngineConfiguration(
        koinModule: Module = this@ServerApplication.koinModule,
        config: ApplicationConfig = this@ServerApplication.config,
    ) {
        engineConfigurers.withEach {
            configure(koinModule, config)
        }
    }

    public fun ApplicationEnvironmentBuilder.applyEnvironmentConfiguration(
        koinModule: Module = this@ServerApplication.koinModule,
        config: ApplicationConfig = this@ServerApplication.config,
    ) {
        this.config = config

        this.applyEnvironmentConfigurers(koinModule, config)
    }

    public fun Application.applyApplicationConfigurersAndRoutes(
        koinModule: Module = this@ServerApplication.koinModule,
        config: ApplicationConfig = environment.config,
    ) {
        applicationConfigurers.withEach {
            configure(koinModule, config)
        }

        installRoutes()
    }

    private fun ApplicationEnvironmentBuilder.applyEnvironmentConfigurers(
        koinModule: Module,
        config: ApplicationConfig,
    ) {
        environmentConfigurers.withEach {
            configure(koinModule, config)
        }
    }

    private fun Application.installRoutes() {
        try {
            routing {
                installRoutes()
            }
        } catch (_: NoDefinitionFoundException) {
        }
    }

    private fun makeHoconApplicationConfig(): ApplicationConfig {
        val loadedHoconConfig = ConfigFactory.load()

        return HoconApplicationConfig(loadedHoconConfig)
    }
}