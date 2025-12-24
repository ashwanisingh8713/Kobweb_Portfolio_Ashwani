package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.components.rememberAnimatedText
import com.mano.ashwa.styles.GradientTagLineStyle
import com.mano.ashwa.styles.upDownAnim
import com.mano.ashwa.styles.zoomIn
import com.mano.ashwa.utils.Assets
import com.mano.ashwa.utils.atBreakpointMd
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.BackgroundPosition
import com.varabyte.kobweb.compose.css.BackgroundRepeat
import com.varabyte.kobweb.compose.css.BackgroundSize
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import org.jetbrains.compose.web.css.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P

@Composable
fun Banner() {
    val current = LocalAppColorMode.current.value
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT
    val textColor = if (isLight) Colors.Black else Colors.White

    // Theme-aware banner background
    val bannerBgUrl = if (isLight) "/assets/banner-bg-light.svg" else "/assets/banner-bg-dark.svg"

    val animatedText = rememberAnimatedText(
        toRotate = listOf("Android Tech Lead", "KMP Developer","Kobweb Developer"),
        period = 2000
    )
    SimpleGrid(
        numColumns(base = 1, md = 2),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.px, leftRight = 16.px, bottom = 40.px) // Reduced padding for mobile
            .backgroundImage(url(bannerBgUrl))
            .background {
                position(BackgroundPosition.of(CSSPosition.Center))
                size(BackgroundSize.Cover)
                repeat(BackgroundRepeat.NoRepeat)
            }
            .id("home")
    ) {
        BannerText(animatedText.value, textColor)
        Image(
            src = Assets.HeaderImg,
            modifier = Modifier
                .width(100.percent)
                .maxWidth(400.px)
                .padding(top = 20.px)
                .height(auto)
                .animation(
                    zoomIn.toAnimation(
                        duration = 1.s,
                        timingFunction = AnimationTimingFunction.Ease,
                        iterationCount = AnimationIterationCount.of(1)
                    ),
                    upDownAnim.toAnimation(
                        duration = 2.s,
                        direction = AnimationDirection.Alternate,
                        iterationCount = AnimationIterationCount.Infinite
                    )
                )
        )
    }
}

@Composable
fun BannerText(text: String, textColor: com.varabyte.kobweb.compose.ui.graphics.Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(leftRight = 8.px, topBottom = 20.px)
    ) {
        SpanText(
            text = "Welcome to my Portfolio",
            modifier = GradientTagLineStyle.toModifier()
        )
        H1(
            attrs = Modifier
                .fontSize(32.px)
                .fontWeight(700)
                .letterSpacing(0.80.px)
                .lineHeight(1.2)
                .margin(bottom = 16.px)
                .display(DisplayStyle.Block)
                .toAttrs()
        ) {
            SpanText(
                text = "Hi! I'm ",
                modifier = Modifier.color(textColor)
            )
            SpanText(
                text = "Ashwani",
                modifier = Modifier
                    .color(Color("#3C83EF"))
            )
            SpanText(
                text = " $text",
                modifier = Modifier.borderRight(
                    width = 0.08.em,
                    style = LineStyle.Solid,
                ).color(textColor)
            )
        }
        P(
            attrs = Modifier
                .fontSize(14.px)
                .letterSpacing(0.5.px)
                .lineHeight(1.6.em)
                .width(100.percent)
                .toAttrs()
        ) {
            SpanText("""A self-taught software developer and Tech Lead with a strong passion for modern technology.
            Skilled in leading teams, collaborating with clients, and delivering high-quality, impactful digital solutions that drive business success."""
            ,modifier = GradientTagLineStyle.toModifier())
        }
    }
}
