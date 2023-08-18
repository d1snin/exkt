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

package dev.d1s.exkt.common.pagination

/**
 * The main purpose of [Paginator] is to produce [limit and offset][LimitAndOffset]
 * based on the given page limit and current page.
 */
public interface Paginator {

    public val pageLimit: Int

    public var currentPage: Int

    public val limit: Int

    public val offset: Int

    public val limitAndOffset: LimitAndOffset

    public fun isLast(totalElements: Int): Boolean
}

public fun Paginator(pageLimit: Int, currentPage: Int): Paginator =
    DefaultPaginator(pageLimit, currentPage)

internal class DefaultPaginator(
    override val pageLimit: Int,
    override var currentPage: Int
) : Paginator {

    override val limit: Int
        get() = pageLimit * currentPage

    override val offset: Int
        get() = (pageLimit * currentPage) - pageLimit

    override val limitAndOffset: LimitAndOffset
        get() = LimitAndOffset(limit, offset)

    override fun isLast(totalElements: Int) =
        totalElements in (offset + 1)..limit
}