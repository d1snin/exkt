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

import io.kvision.core.Component

public fun Component.dNone(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("none", print, breakpoint)
}

public fun Component.dInline(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("inline", print, breakpoint)
}

public fun Component.dInlineBlock(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("inline-block", print, breakpoint)
}

public fun Component.dBlock(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("block", print, breakpoint)
}

public fun Component.dGrid(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("grid", print, breakpoint)
}

public fun Component.dInlineGrid(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("inline-grid", print, breakpoint)
}

public fun Component.dTable(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("table", print, breakpoint)
}

public fun Component.dTableCell(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("table-cell", print, breakpoint)
}

public fun Component.dTableRow(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("table-row", print, breakpoint)
}

public fun Component.dFlex(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("flex", print, breakpoint)
}

public fun Component.dInlineFlex(print: Boolean = false, breakpoint: Breakpoint? = null) {
    d("inline-flex", print, breakpoint)
}

private fun Component.d(value: String, print: Boolean, breakpoint: Breakpoint?) {
    addCssClass(
        ("d-" + if (print) "print-" else "")
            .appendBreakpointWithValue(breakpoint, value)
    )
}