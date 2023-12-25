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

public fun Component.flexRow(breakpoint: Breakpoint? = null) {
    flexDirection("row", breakpoint)
}

public fun Component.flexRowReverse(breakpoint: Breakpoint? = null) {
    flexDirection("row-reverse", breakpoint)
}

public fun Component.flexColumn(breakpoint: Breakpoint? = null) {
    flexDirection("column", breakpoint)
}

public fun Component.flexColumnReverse(breakpoint: Breakpoint? = null) {
    flexDirection("column-reverse", breakpoint)
}

public fun Component.justifyContentStart(breakpoint: Breakpoint? = null) {
    justifyContent("start", breakpoint)
}

public fun Component.justifyContentEnd(breakpoint: Breakpoint? = null) {
    justifyContent("end", breakpoint)
}

public fun Component.justifyContentCenter(breakpoint: Breakpoint? = null) {
    justifyContent("center", breakpoint)
}

public fun Component.justifyContentBetween(breakpoint: Breakpoint? = null) {
    justifyContent("between", breakpoint)
}

public fun Component.justifyContentAround(breakpoint: Breakpoint? = null) {
    justifyContent("around", breakpoint)
}

public fun Component.justifyContentEvenly(breakpoint: Breakpoint? = null) {
    justifyContent("evenly", breakpoint)
}

public fun Component.alignItemsStart(breakpoint: Breakpoint? = null) {
    alignItems("start", breakpoint)
}

public fun Component.alignItemsEnd(breakpoint: Breakpoint? = null) {
    alignItems("end", breakpoint)
}

public fun Component.alignItemsCenter(breakpoint: Breakpoint? = null) {
    alignItems("center", breakpoint)
}

public fun Component.alignItemsBaseline(breakpoint: Breakpoint? = null) {
    alignItems("baseline", breakpoint)
}

public fun Component.alignItemsStretch(breakpoint: Breakpoint? = null) {
    alignItems("stretch", breakpoint)
}

public fun Component.alignSelfStart(breakpoint: Breakpoint? = null) {
    alignSelf("start", breakpoint)
}

public fun Component.alignSelfEnd(breakpoint: Breakpoint? = null) {
    alignSelf("end", breakpoint)
}

public fun Component.alignSelfCenter(breakpoint: Breakpoint? = null) {
    alignSelf("center", breakpoint)
}

public fun Component.alignSelfBaseline(breakpoint: Breakpoint? = null) {
    alignSelf("baseline", breakpoint)
}

public fun Component.alignSelfStretch(breakpoint: Breakpoint? = null) {
    alignSelf("stretch", breakpoint)
}

private fun Component.flexDirection(value: String, breakpoint: Breakpoint?) {
    addCssClass(
        "flex-".appendBreakpointWithValue(breakpoint, value)
    )
}

public fun Component.flexGrow0(breakpoint: Breakpoint? = null) {
    flexGrow(0, breakpoint)
}

public fun Component.flexGrow1(breakpoint: Breakpoint? = null) {
    flexGrow(1, breakpoint)
}

public fun Component.flexShrink0(breakpoint: Breakpoint? = null) {
    flexShrink(0, breakpoint)
}

public fun Component.flexShrink1(breakpoint: Breakpoint? = null) {
    flexShrink(1, breakpoint)
}

private fun Component.justifyContent(value: String, breakpoint: Breakpoint?) {
    addCssClass(
        "justify-content-".appendBreakpointWithValue(breakpoint, value)
    )
}

private fun Component.alignItems(value: String, breakpoint: Breakpoint?) {
    addCssClass(
        "align-items-".appendBreakpointWithValue(breakpoint, value)
    )
}

private fun Component.alignSelf(value: String, breakpoint: Breakpoint?) {
    addCssClass(
        "align-self-".appendBreakpointWithValue(breakpoint, value)
    )
}

private fun Component.flexGrow(value: Int, breakpoint: Breakpoint?) {
    addCssClass(
        "flex-".appendBreakpointWithValue(breakpoint, "grow") + "-$value"
    )
}

private fun Component.flexShrink(value: Int, breakpoint: Breakpoint?) {
    addCssClass(
        "flex-".appendBreakpointWithValue(breakpoint, "shrink") + "-$value"
    )
}