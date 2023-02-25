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

import io.ktor.server.application.*
import io.ktor.server.config.*
import org.koin.core.module.Module

/**
 * [Configurer] supposed to configure [Application].
 */
public interface ApplicationConfigurer : Configurer<Application> {

    /**
     * Configures [Application].
     *
     * **Please note that [Application.environment]'s [ApplicationConfig] should not be used to retrieve configuration.
     * Use the provided [config] parameter instead.**
     */
    override fun Application.configure(module: Module, config: ApplicationConfig)
}