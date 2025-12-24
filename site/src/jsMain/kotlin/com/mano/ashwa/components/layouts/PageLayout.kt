package com.mano.ashwa.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.sections.BSHeader
import com.mano.ashwa.sections.Footer
import com.mano.ashwa.toSitePalette
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.vh

val PageContentStyle = CssStyle {
    base {
        Modifier.fillMaxSize()
            .padding(leftRight = 2.cssRem, top = 4.cssRem)
    }
}

class PageLayoutData(val title: String)

@Composable
@Layout
fun PageLayout(ctx: PageContext, content: @Composable ColumnScope.() -> Unit) {
    val data = ctx.data.getValue<PageLayoutData>()
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()

    // Text color based on theme
    val textColor = if (current == ColorMode.LIGHT)
        com.varabyte.kobweb.compose.ui.graphics.Color.rgb(0x1E293B)
    else
        Colors.White

    LaunchedEffect(data.title) {
        document.title = data.title
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .minHeight(100.vh)
                .backgroundColor(sitePal.nearBackground)
                .color(textColor)
                .fontFamily("Centra", "sans-serif"),
        ) {
            BSHeader(ctx)
            content()
            Footer()
        }
    }

}
