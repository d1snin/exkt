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

public fun Component.textStart(breakpoint: Breakpoint? = null) {
    textAlignment("start", breakpoint)
}

public fun Component.textCenter(breakpoint: Breakpoint? = null) {
    textAlignment("center", breakpoint)
}

public fun Component.textEnd(breakpoint: Breakpoint? = null) {
    textAlignment("end", breakpoint)
}

public fun Component.textWrap() {
    addCssClass("text-wrap")
}

public fun Component.textNoWrap() {
    addCssClass("text-nowrap")
}

public fun Component.textBreak() {
    addCssClass("text-break")
}

public fun Component.textLowercase() {
    addCssClass("text-lowercase")
}

public fun Component.textUppercase() {
    addCssClass("text-uppercase")
}

public fun Component.textCapitalize() {
    addCssClass("text-capitalize")
}

public fun Component.fs1() {
    addCssClass("fs-1")
}

public fun Component.fs2() {
    addCssClass("fs-2")
}

public fun Component.fs3() {
    addCssClass("fs-3")
}

public fun Component.fs4() {
    addCssClass("fs-4")
}

public fun Component.fs5() {
    addCssClass("fs-5")
}

public fun Component.fs6() {
    addCssClass("fs-6")
}

public fun Component.fwBold() {
    addCssClass("fw-bold")
}

public fun Component.fwBolder() {
    addCssClass("fw-bolder")
}

public fun Component.fwSemibold() {
    addCssClass("fw-semibold")
}

public fun Component.fwMedium() {
    addCssClass("fw-medium")
}

public fun Component.fwNormal() {
    addCssClass("fw-normal")
}

public fun Component.fwLight() {
    addCssClass("fw-light")
}

public fun Component.fwLighter() {
    addCssClass("fw-lighter")
}

public fun Component.fstItalic() {
    addCssClass("fst-italic")
}

public fun Component.fstNormal() {
    addCssClass("fst-normal")
}

public fun Component.lh1() {
    addCssClass("lh-1")
}

public fun Component.lhSm() {
    addCssClass("lh-sm")
}

public fun Component.lhBase() {
    addCssClass("lh-base")
}

public fun Component.lhLg() {
    addCssClass("lh-lg")
}

public fun Component.fontMonospace() {
    addCssClass("font-monospace")
}

public fun Component.textReset() {
    addCssClass("text-reset")
}

public fun Component.textDecorationUnderline() {
    addCssClass("text-decoration-underline")
}

public fun Component.textDecorationLineThrough() {
    addCssClass("text-decoration-line-through")
}

public fun Component.textDecorationNone() {
    addCssClass("text-decoration-none")
}

private fun Component.textAlignment(value: String, breakpoint: Breakpoint?) {
    addCssClass(
        "text-".appendBreakpointWithValue(breakpoint, value)
    )
}