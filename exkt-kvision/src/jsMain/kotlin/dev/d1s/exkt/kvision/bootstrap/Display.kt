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

public fun Component.displayNone(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("none", print, breakpoint)
}

public fun Component.displayInline(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("inline", print, breakpoint)
}

public fun Component.displayInlineBlock(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("inline-block", print, breakpoint)
}

public fun Component.displayBlock(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("block", print, breakpoint)
}

public fun Component.displayGrid(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("grid", print, breakpoint)
}

public fun Component.displayInlineGrid(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("inline-grid", print, breakpoint)
}

public fun Component.displayTable(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("table", print, breakpoint)
}

public fun Component.displayTableCell(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("table-cell", print, breakpoint)
}

public fun Component.displayTableRow(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("table-row", print, breakpoint)
}

public fun Component.displayFlex(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("flex", print, breakpoint)
}

public fun Component.displayInlineFlex(print: Boolean = false, breakpoint: Breakpoint? = null) {
    display("inline-flex", print, breakpoint)
}

private fun Component.display(value: String, print: Boolean, breakpoint: Breakpoint?) {
    addCssClass(
        ("display-" + if (print) "print-" else "")
            .appendBreakpointWithValue(breakpoint, value)
    )
}