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
 * This class is intended to store data about [limit] and [offset] parameters.
 *
 * ```
 * page limit: 20
 *
 * limit:   20  40  60  80  100  120
 * offset:   0  20  40  60  80   100
 *           |---|---|---|---|---|
 *           1   2   3   4   5   6
 *                  (page)
 * ```
 * @see Paginator
 */
public data class LimitAndOffset(
    /**
     * Limit is used to reduce the amount of final elements from the end.
     */
    val limit: Int,

    /**
     * Offset is used to omit given amount of elements from the start.
     */
    val offset: Int
)
