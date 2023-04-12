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

package dev.d1s.exkt.kvision.component

import io.kvision.panel.SimplePanel

public typealias ConfigCreator<TConfig> = () -> TConfig

/**
 * This class defines a very simple UI component abstraction.
 * Every [Component] is supposed to be rendered inside other [Components][Component] forming a tree
 * where the head is [Component.Root].
 *
 * Components are being rendered through [render][dev.d1s.exkt.kvision.component.render] extension function.
 *
 * Example usage:
 * ```kotlin
 * object MessageComponent : Component<MessageComponent.MyComponentConfig>(::MyComponentConfig) {
 *
 *     override suspend fun SimplePanel.render() {
 *         h1(config.message)
 *     }
 *
 *     class MyComponentConfig {
 *
 *         var message: String = "Hello, World!"
 *     }
 * }
 *
 * object RootComponent : Component.Root() {
 *
 *     override suspend fun SimplePanel.render() {
 *         render(MessageComponent) {
 *             message = "Bye, World!"
 *         }
 *     }
 * }
 *
 * class MyApplication : Application() {
 *
 *     override fun start() {
 *         root("root") {
 *             render(RootComponent)
 *         }
 *     }
 * }
 * ```
 *
 * @param TConfig defines any configuration class to be used to configure this component.
 * This must be [Unit] if you don't introduce any configuration for this component.
 * Also note that [config] property must be implemented with not-null value in case you want to make this component configurable.
 *
 * @param configCreator creator of the provided [TConfig].
 *
 * @see Component.Root
 * @see dev.d1s.exkt.kvision.component.render
 */
public abstract class Component<TConfig : Any>(private val configCreator: ConfigCreator<TConfig>? = null) {

    @Suppress("MemberVisibilityCanBePrivate")
    protected val config: TConfig by lazy {
        val creator = configCreator ?: error("config is not available")

        creator.invoke()
    }

    /**
     * Renders this component on [SimplePanel].
     */
    public abstract suspend fun SimplePanel.render()

    /**
     * Applies [TConfig] configuration for this component.
     */
    public fun apply(block: TConfig.() -> Unit) {
        config.apply(block)
    }

    /**
     * Root component. Supposed to be rendered right in the [Root][io.kvision.panel.Root] panel.
     * Note that [Component.Root] can not be configured.
     */
    public abstract class Root : Component<Unit>()
}