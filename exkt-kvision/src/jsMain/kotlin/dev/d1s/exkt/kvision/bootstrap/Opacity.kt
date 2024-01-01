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

public fun Component.opacity100() {
    addCssClass("opacity-100")
}

public fun Component.opacity75() {
    addCssClass("opacity-75")
}

public fun Component.opacity50() {
    addCssClass("opacity-50")
}

public fun Component.opacity25() {
    addCssClass("opacity-25")
}

public fun Component.opacity0() {
    addCssClass("opacity-0")
}