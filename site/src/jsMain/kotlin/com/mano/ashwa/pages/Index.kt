package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Div
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.sections.ResumeSection
import com.mano.ashwa.navigation.Screen
import com.mano.ashwa.toSitePalette
import com.stevdza.san.kotlinbs.components.BSButton
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px

// Container that has a tagline and grid on desktop, and just the tagline on mobile
val HeroContainerStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

// A demo grid that appears on the homepage because it looks good
val HomeGridStyle = CssStyle.base {
    Modifier
        .gap(0.5.cssRem)
        .width(70.cssRem)
        .height(18.cssRem)
}

private val GridCellColorVar by StyleVariable<Color>()
val HomeGridCellStyle = CssStyle.base {
    Modifier
        .backgroundColor(GridCellColorVar.value())
        .boxShadow(blurRadius = 0.6.cssRem, color = GridCellColorVar.value())
        .borderRadius(1.cssRem)
}

@Composable
private fun GridCell(color: Color, row: Int, column: Int, width: Int? = null, height: Int? = null) {
    Div(
        HomeGridCellStyle.toModifier()
            .setVariable(GridCellColorVar, color)
            .gridItem(row, column, width, height)
            .toAttrs()
    )
}


@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Ashwani Kumar"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage(ctx: PageContext) {
    // When @Layout is used, then mentioned "PageLayout" UI comes automatically in calling page.
    // the "content" parameter which is here as lambda, gets remaining UI of the page
    // Below full code is referred from PageLayout.kt -> PageLayout function parameter 'content'
    // here whatever is written those will go inside "content" parameter, because it has last parameter as Lambda function
    Row(HeroContainerStyle.toModifier()) {
        Box {
            val sitePalette = ColorMode.current.toSitePalette()

            Column(Modifier.gap(2.cssRem)) {
                // Write this resume in Kobweb Compose App.
                //  /Users/ashwani/Kobweb/Kobweb_Portfolio_Ashwani/site/Resume/Ashwani Android Tech Lead & KMP Developer.pdf
                ResumeSection()
            }
        }
    }
}
