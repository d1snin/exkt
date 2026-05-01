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

package dev.d1s.exkt.kvision.bootstrap

import io.kvision.core.Component

public fun Component.overflowAuto() {
    overflow("auto", axis = null)
}

public fun Component.overflowHidden() {
    overflow("hidden", axis = null)
}

public fun Component.overflowVisible() {
    overflow("visible", axis = null)
}

public fun Component.overflowScroll() {
    overflow("scroll", axis = null)
}

public fun Component.overflowXAuto() {
    overflow("auto", axis = "x")
}

public fun Component.overflowXHidden() {
    overflow("hidden", axis = "x")
}

public fun Component.overflowXVisible() {
    overflow("visible", axis = "x")
}

public fun Component.overflowXScroll() {
    overflow("scroll", axis = "x")
}

public fun Component.overflowYAuto() {
    overflow("auto", axis = "y")
}

public fun Component.overflowYHidden() {
    overflow("hidden", axis = "y")
}

public fun Component.overflowYVisible() {
    overflow("visible", axis = "y")
}

public fun Component.overflowYScroll() {
    overflow("scroll", axis = "y")
}

private fun Component.overflow(value: String, axis: String?) {
    addCssClass(
        "overflow" + (axis?.let { "-$it" } ?: "") + "-$value"
    )
}