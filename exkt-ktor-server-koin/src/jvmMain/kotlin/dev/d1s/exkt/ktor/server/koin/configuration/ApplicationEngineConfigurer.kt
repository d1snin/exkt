package dev.d1s.exkt.ktor.server.koin.configuration

import io.ktor.server.engine.*

/**
 * [Configurer] supposed to configure application engine like Netty.
 * i. e. `connector`, `rootPath`
 */
public interface ApplicationEngineConfigurer<TEngineConfig : BaseApplicationEngine.Configuration> : Configurer<TEngineConfig>