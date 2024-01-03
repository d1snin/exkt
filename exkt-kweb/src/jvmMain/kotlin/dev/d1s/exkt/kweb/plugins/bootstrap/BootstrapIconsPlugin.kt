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

package dev.d1s.exkt.kweb.plugins.bootstrap

import kweb.plugins.KwebPlugin
import org.jsoup.nodes.Document

public class BootstrapIconsPlugin : KwebPlugin() {

    override fun decorate(doc: Document) {
        doc.head().appendElement("link")
            .attr("rel", "stylesheet")
            .attr("type", "text/css")
            .attr("href", BOOTSTRAP_ICONS_CSS_URL)
    }

    private companion object {

        private const val BOOTSTRAP_ICONS_CSS_URL =
            "https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css"
    }
}

public val bootstrapIconsPlugin: BootstrapIconsPlugin get() = BootstrapIconsPlugin()

public val bootstrapIcons: BootstrapIconsClasses = BootstrapIconsClasses()