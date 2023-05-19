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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private val renderingScope by lazy {
    CoroutineScope(Dispatchers.Main)
}

/**
 * Renders [component] on this [SimplePanel].
 */
public fun SimplePanel.render(component: Component<*>): Job =
    with(component) {
        renderingScope.launch {
            render()
        }
    }

/**
 * Applies [config] to [component] and renders it on this [SimplePanel].
 */
public fun <TConfig : Any> SimplePanel.render(component: Component<TConfig>, config: TConfig.() -> Unit) {
    component.apply(config)

    render(component)
}
