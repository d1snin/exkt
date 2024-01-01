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

package dev.d1s.exkt.konform

import io.konform.validation.Constraint
import io.konform.validation.ValidationBuilder
import io.konform.validation.jsonschema.maxLength
import io.konform.validation.jsonschema.minLength

public fun ValidationBuilder<String>.isEmpty(): Constraint<String> =
    maxLength(0)

public fun ValidationBuilder<String>.isNotEmpty(): Constraint<String> =
    minLength(1)

public fun ValidationBuilder<String>.isBlank(): Constraint<String> =
    addConstraint("must be blank") {
        it.isBlank()
    }

public fun ValidationBuilder<String>.isNotBlank(): Constraint<String> =
    addConstraint("must not be blank") {
        it.isNotBlank()
    }

public fun ValidationBuilder<String>.matches(regex: Regex): Constraint<String> =
    addConstraint("must match the expression: {0}", regex.toString()) {
        it.matches(regex)
    }