package sample

import elide.server.*
import elide.server.annotations.Page
import elide.server.controller.PageController
import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Get
import io.micronaut.runtime.Micronaut.build
import kotlinx.css.Color
import kotlinx.css.backgroundColor
import kotlinx.css.fontFamily
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.strong
import kotlinx.html.title

/** Self-contained application example, which serves an HTML page, with CSS, that says "Hello, Elide!". */
object App {
    /** GET `/`: Controller for index page. */
    @Page class Index : PageController() {
        // Serve the page itself.
        @Get("/") suspend fun index() = html {
            head {
                title { +"Hello, Elide!" }
                stylesheet("/styles/main.css")
                script("/scripts/ui.js", defer = true)
            }
            body {
                strong { +"Hello, Elide!" }
            }
        }

        // Serve styles for the page.
        @Get("/styles/main.css") fun styles() = css {
            rule("body") {
                backgroundColor = Color("#bada55")
            }
            rule("strong") {
                fontFamily = "-apple-system, BlinkMacSystemFont, sans-serif"
            }
        }

        // Serve the built & embedded JavaScript.
        @Get("/scripts/ui.js") suspend fun js(request: HttpRequest<Any>) = script(request) {
            module("scripts.ui")
        }
    }

    /** Main entrypoint for the application. */
    @JvmStatic fun main(args: Array<String>) {
        build().args(*args).start()
    }
}
