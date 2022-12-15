package dsl

import kotlinx.html.FlowOrPhrasingContent
import kotlinx.html.HtmlTagMarker

typealias DslBlock = DrekContent.() -> Unit
typealias DslListBlock = DrekContent.() -> Unit

@HtmlTagMarker
abstract class DrekListContent {
    @HtmlTagMarker
    abstract fun item(block: DslBlock)

    @HtmlTagMarker
    fun item(text: String) = item { text(text) }
}

@HtmlTagMarker /* lazily re-use this marker from kotlinx for now */
abstract class DrekContent {
    @HtmlTagMarker
    abstract fun text(text: String)

    @HtmlTagMarker
    abstract fun em(block: DslBlock)

    @HtmlTagMarker
    abstract fun strong(block: DslBlock)

    @HtmlTagMarker
    abstract fun h1(block: DslBlock)
    @HtmlTagMarker
    abstract fun h2(block: DslBlock)
    @HtmlTagMarker
    abstract fun h3(block: DslBlock)
    @HtmlTagMarker
    abstract fun h4(block: DslBlock)
    @HtmlTagMarker
    abstract fun h5(block: DslBlock)
    @HtmlTagMarker
    abstract fun h6(block: DslBlock)

    @HtmlTagMarker
    abstract fun quote(block: DslBlock)

    @HtmlTagMarker
    abstract fun code(
        inline: Boolean, /* always inline if there are no line breaks */
        code: String
    )

    @HtmlTagMarker
    abstract fun ordered(builder: DslListBlock)

    @HtmlTagMarker
    abstract fun unordered(builder: DslListBlock)

    @HtmlTagMarker
    fun em(text: String) = em { text(text) }
    @HtmlTagMarker
    fun strong(text: String) = strong { text(text) }

    @HtmlTagMarker
    fun h1(text: String) = h1 { text(text) }
    @HtmlTagMarker
    fun h2(text: String) = h2 { text(text) }
    @HtmlTagMarker
    fun h3(text: String) = h3 { text(text) }
    @HtmlTagMarker
    fun h4(text: String) = h4 { text(text) }
    @HtmlTagMarker
    fun h5(text: String) = h5 { text(text) }
    @HtmlTagMarker
    fun h6(text: String) = h6 { text(text) }

    @HtmlTagMarker
    fun quote(text: String) = quote { text(text) }

    @HtmlTagMarker
    fun code(code: String) = code(true, code)
}

@HtmlTagMarker
fun FlowOrPhrasingContent.drek(builder: DslBlock) {

}