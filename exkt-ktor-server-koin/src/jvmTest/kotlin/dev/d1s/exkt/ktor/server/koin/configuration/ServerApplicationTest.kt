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

import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.routing.*
import io.mockk.*
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertIs

class ServerApplicationTest {

    private val serverApplication = spyk(makeServerApplication())

    private val environmentConfigurerMock = mockk<EnvironmentConfigurer>(relaxUnitFun = true)
    private val applicationConfigurerMock = mockk<ApplicationConfigurer>(relaxUnitFun = true)

    private val routeInstallerMock = mockk<RouteInstaller>(relaxUnitFun = true)

    private val koinModuleMock = mockk<Module>()

    private val applicationEngineEnvironmentBuilderMock = makeApplicationEngineEnvironmentBuilderMock()

    private val applicationConfigMock = mockk<ApplicationConfig>()

    private val applicationMock = mockk<Application>()

    private val routingMock = mockk<Routing>()

    @BeforeTest
    fun `configure route installer`() {
        startKoin {
            val testModule = module {
                single {
                    routeInstallerMock
                }
            }

            modules(testModule)
        }
    }

    @AfterTest
    fun `stop koin`() {
        stopKoin()
    }

    @Test
    fun `must create application engine environment and configure it`() {
        withStaticApplicationEngineEnvironmentMock {
            withStaticRoutingMock {
                val applicationEngineEnvironment = serverApplication.createApplicationEngineEnvironment(
                    koinModuleMock,
                    applicationConfigMock
                )

                assertIs<ApplicationEngineEnvironment>(applicationEngineEnvironment)

                verify {
                    with(serverApplication) {
                        applicationEngineEnvironmentBuilderMock.applyEnvironmentConfiguration(
                            koinModuleMock,
                            applicationConfigMock
                        )
                    }
                }
            }
        }
    }

    @Test
    fun `must apply environment configuration`() {
        withStaticRoutingMock {
            with(serverApplication) {
                applicationEngineEnvironmentBuilderMock.applyEnvironmentConfiguration(
                    koinModuleMock,
                    applicationConfigMock
                )
            }

            verifyAll {
                applicationEngineEnvironmentBuilderMock.config = applicationConfigMock

                applicationEngineEnvironmentBuilderMock.module(any())
            }
        }
    }

    @Test
    fun `must apply application configuration and routes`() {
        withStaticRoutingMock {
            with(serverApplication) {
                applicationMock.applyApplicationConfigurersAndRoutes(koinModuleMock, applicationConfigMock)
            }

            verify {
                with(routeInstallerMock) {
                    routingMock.installRoutes()
                }
            }
        }
    }

    private fun makeServerApplication() = object : ServerApplication() {

        override val configurers = listOf(environmentConfigurerMock, applicationConfigurerMock)

        override fun launch() {
        }
    }

    private inline fun withStaticApplicationEngineEnvironmentMock(block: () -> Unit) {
        mockkStatic("io.ktor.server.engine.ApplicationEngineEnvironmentKt") {
            val slot = slot<ApplicationEngineEnvironmentBuilder.() -> Unit>()

            every {
                applicationEngineEnvironment(capture(slot))
            } answers {
                slot.captured.invoke(applicationEngineEnvironmentBuilderMock)

                mockk()
            }

            block()
        }
    }

    private fun makeApplicationEngineEnvironmentBuilderMock() = mockk<ApplicationEngineEnvironmentBuilder>() {
        justRun {
            config = any()
        }

        val slot = slot<Application.() -> Unit>()

        every {
            module(capture(slot))
        } answers {
            slot.captured.invoke(applicationMock)
        }
    }

    private inline fun withStaticRoutingMock(block: () -> Unit) {
        mockkStatic("io.ktor.server.routing.RoutingKt") {
            val slot = slot<Routing.() -> Unit>()

            every {
                applicationMock.routing(capture(slot))
            } answers {
                slot.captured.invoke(routingMock)

                routingMock
            }

            block()
        }
    }
}