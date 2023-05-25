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
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Synchronously renders [component] on this [SimplePanel].
 */
public fun SimplePanel.render(component: Component<*>) {
    with(component) {
        render()
    }
}

/**
 * Applies [config] to [component] and synchronously renders it on this [SimplePanel].
 */
public fun <TConfig : Any> SimplePanel.render(component: Component<TConfig>, config: TConfig.() -> Unit) {
    component.apply(config)

    render(component)
}

/**
 * Launches [component]'s [render][Component.render] on this [SimplePanel].
 */
public suspend fun SimplePanel.launch(component: Component<*>): Job =
    with(component) {
        coroutineScope {
            launch {
                render()
            }
        }
    }

/**
 * Applies [config] to [component] and launches [render][Component.render] on this [SimplePanel].
 */
public suspend fun <TConfig : Any> SimplePanel.launch(component: Component<TConfig>, config: TConfig.() -> Unit) {
    component.apply(config)

    launch(component)
}
