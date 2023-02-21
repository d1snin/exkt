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

package dev.d1s.exkt.ktorm.util

import org.ktorm.entity.Entity
import kotlin.reflect.KClass

class TestEntity : Entity<TestEntity> {

    private val hashCode = Any().hashCode()

    override val entityClass: KClass<TestEntity>
        get() = TODO("Not yet implemented")
    override val properties: Map<String, Any?>
        get() = TODO("Not yet implemented")

    override fun copy(): TestEntity {
        TODO("Not yet implemented")
    }

    override fun delete(): Int {
        TODO("Not yet implemented")
    }

    override fun discardChanges() {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        TODO("Not yet implemented")
    }

    override fun flushChanges(): Int {
        TODO("Not yet implemented")
    }

    override fun get(name: String): Any? {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int = hashCode

    override fun set(name: String, value: Any?) {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }
}

val testEntity get() = TestEntity()