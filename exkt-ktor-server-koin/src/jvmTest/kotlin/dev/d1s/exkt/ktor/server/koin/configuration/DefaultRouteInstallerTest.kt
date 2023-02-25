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

import io.ktor.server.routing.*
import io.mockk.mockk
import io.mockk.verify
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class DefaultRouteInstallerTest {

    private val routeInstaller = DefaultRouteInstaller()

    private val routingMock = mockk<Routing>()

    private val routeMock = mockk<Route>(relaxUnitFun = true)

    @BeforeTest
    fun `configure koin`() {
        startKoin {
            val testModule = module {
                single {
                    routeMock
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
    fun `must install routes`() {
        with(routeInstaller) {
            routingMock.installRoutes()

            with(routeMock) {
                verify {
                    routingMock.apply()
                }
            }
        }
    }
}