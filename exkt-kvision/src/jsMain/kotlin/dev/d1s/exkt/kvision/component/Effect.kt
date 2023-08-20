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

import io.kvision.state.ObservableState
import io.kvision.state.ObservableValue

/**
 * Effect of rendering a [Component].
 *
 * @see Component
 */
public interface Effect {

    public val success: Boolean

    public companion object {

        public val Success: Effect = SimpleEffect(success = true)
        public val Failure: Effect = SimpleEffect(success = false)
    }
}

public data class SimpleEffect(override val success: Boolean) : Effect

public typealias LazyEffectState = ObservableState<Boolean>
public typealias MutableLazyEffectState = ObservableValue<Boolean>

public class LazyEffect(public val state: LazyEffectState) : Effect {

    override val success: Boolean
        get() = state.getState()
}

public fun lazyEffect(): Pair<MutableLazyEffectState, LazyEffect> =
    ObservableValue(true).let {
        it to LazyEffect(it)
    }