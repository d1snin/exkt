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

package dev.d1s.exkt.kvision.bootstrap

import dev.d1s.exkt.common.takeIfOrEmpty
import io.kvision.core.Component

public fun Component.linkOpacity10(hover: Boolean = false, underline: Boolean = false) {
    linkOpacity("10", hover, underline)
}

public fun Component.linkOpacity25(hover: Boolean = false, underline: Boolean = false) {
    linkOpacity("25", hover, underline)
}

public fun Component.linkOpacity50(hover: Boolean = false, underline: Boolean = false) {
    linkOpacity("50", hover, underline)
}

public fun Component.linkOpacity75(hover: Boolean = false, underline: Boolean = false) {
    linkOpacity("75", hover, underline)
}

public fun Component.linkOpacity100(hover: Boolean = false, underline: Boolean = false) {
    linkOpacity("100", hover, underline)
}

public fun Component.linkUnderline() {
    addCssClass("link-underline")
}

public fun Component.linkUnderlinePrimary() {
    addCssClass("link-underline-primary")
}

public fun Component.linkUnderlineSecondary() {
    addCssClass("link-underline-secondary")
}

public fun Component.linkUnderlineSuccess() {
    addCssClass("link-underline-success")
}

public fun Component.linkUnderlineDanger() {
    addCssClass("link-underline-danger")
}

public fun Component.linkUnderlineWarning() {
    addCssClass("link-underline-warning")
}

public fun Component.linkUnderlineInfo() {
    addCssClass("link-underline-info")
}

public fun Component.linkUnderlineLight() {
    addCssClass("link-underline-light")
}

public fun Component.linkUnderlineDark() {
    addCssClass("link-underline-dark")
}

public fun Component.linkOffset1() {
    addCssClass("link-offset-1")
}

public fun Component.linkOffset2() {
    addCssClass("link-offset-2")
}

public fun Component.linkOffset3() {
    addCssClass("link-offset-3")
}

public fun Component.linkPrimary() {
    addCssClass("link-primary")
}

public fun Component.linkSecondary() {
    addCssClass("link-secondary")
}

public fun Component.linkSuccess() {
    addCssClass("link-success")
}

public fun Component.linkDanger() {
    addCssClass("link-danger")
}

public fun Component.linkWarning() {
    addCssClass("link-warning")
}

public fun Component.linkInfo() {
    addCssClass("link-info")
}

public fun Component.linkLight() {
    addCssClass("link-light")
}

public fun Component.linkDark() {
    addCssClass("link-dark")
}

public fun Component.linkBodyEmphasis() {
    addCssClass("link-body-emphasis")
}

private fun Component.linkOpacity(value: String, hover: Boolean, underline: Boolean) {
    addCssClass(
        "link" + "-underline".takeIfOrEmpty { underline } +
                "-opacity-$value" +
                "-hover".takeIfOrEmpty { hover }
    )
}
