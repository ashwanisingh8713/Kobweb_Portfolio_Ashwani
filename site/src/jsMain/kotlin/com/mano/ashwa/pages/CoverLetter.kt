package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.widgets.SkillCardView
import com.mano.ashwa.navigation.CoverLetter_Route
import com.mano.ashwa.pageTitleStyle
import com.mano.ashwa.toSitePalette
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridTemplateColumns
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

@InitRoute
fun initCoverLetterPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Ashwani Cover Letter"))
}

@Page(CoverLetter_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun CoverLetter() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    val textColor = if (current == com.varabyte.kobweb.silk.theme.colors.ColorMode.DARK) Colors.White else Colors.Black
        Column(
            Modifier.fillMaxWidth().backgroundColor(sitePal.nearBackground).gap(32.px),
            //horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText("My Cover Letter", modifier = pageTitleStyle.toModifier().color(textColor).align(alignment = Alignment.CenterHorizontally))

            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .display(DisplayStyle.Grid)
                    .gridTemplateColumns {
                        repeat(4) { minmax(250.px, 1.fr) }
                    }
                    .gap(32.px)
                    .toAttrs()
            ) {
                SpanText("My Cover Letter", modifier = pageTitleStyle.toModifier().color(textColor))
                SpanText("My Cover Letter", modifier = pageTitleStyle.toModifier().color(textColor))

                SpanText("My Cover Letter", modifier = pageTitleStyle.toModifier().color(textColor))
                SpanText("My Cover Letter", modifier = pageTitleStyle.toModifier().color(textColor))
            }
        }
}