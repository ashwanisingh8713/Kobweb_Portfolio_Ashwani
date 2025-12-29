package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.components.rememberAnimatedText
import com.mano.ashwa.styles.GradientTagLineStyle
import com.mano.ashwa.styles.upDownAnim
import com.mano.ashwa.styles.zoomIn
import com.mano.ashwa.utils.Assets
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
import org.jetbrains.compose.web.dom.Div

@Composable
fun Banner() {
    val current = LocalAppColorMode.current.value
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT
    val textColor = if (isLight) Colors.Black else Colors.White

    // Theme-aware banner background
    val bannerBgUrl = if (isLight) "/assets/banner-bg-light.svg" else "/assets/banner-bg-dark.svg"

    val animatedText = rememberAnimatedText(
        toRotate = listOf(
            "Android Tech Lead",
            "Kotlin Multiplatform Developer",
            "Kobweb Developer",
            "GoLang Developer",
            "FullStack Mobile Developer"
        ),
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
        // Enhanced Bio Section
        BioHighlights(textColor)

        // Expertise badges row
        ExpertiseBadges()
    }
}

@Composable
private fun BioHighlights(textColor: com.varabyte.kobweb.compose.ui.graphics.Color) {
    val current = LocalAppColorMode.current.value
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

    val cardBg = if (isLight) "rgba(255, 255, 255, 0.7)" else "rgba(15, 23, 42, 0.7)"
    val borderColor = if (isLight) "rgba(60, 131, 239, 0.2)" else "rgba(60, 131, 239, 0.3)"
    val highlightColor = "#3C83EF"
    val subtleText = if (isLight) "#64748B" else "#94A3B8"

    Div({
        style {
            property("background", cardBg)
            property("backdrop-filter", "blur(10px)")
            property("-webkit-backdrop-filter", "blur(10px)")
            property("border-radius", "16px")
            property("border", "1px solid $borderColor")
            property("padding", "20px 24px")
            property("margin-top", "16px")
            property("margin-bottom", "8px")
        }
    }) {
        // Main intro text
        Div({
            style {
                property("font-size", "16px")
                property("line-height", "1.8")
                property("color", textColor.toString())
                property("margin-bottom", "16px")
            }
        }) {
            SpanText("With ")
            SpanText(
                "14+ years",
                modifier = Modifier
                    .fontWeight(700)
                    .color(Color(highlightColor))
            )
            SpanText(" of experience, I transform ideas into ")
            SpanText(
                "production-ready solutions",
                modifier = Modifier
                    .fontWeight(700)
                    .color(Color(highlightColor))
            )
            SpanText(" — from mobile apps to full-stack systems.")
        }

        // Highlight chips row
        Div({
            style {
                display(DisplayStyle.Flex)
                flexWrap(FlexWrap.Wrap)
                gap(12.px)
                property("margin-bottom", "16px")
            }
        }) {
            listOf(
                "🎯" to "Clean Architecture",
                "👥" to "Team Leadership",
                "🚀" to "Enterprise Apps",
                "🔄" to "Cross-Platform"
            ).forEach { (icon, label) ->
                Div({
                    style {
                        display(DisplayStyle.Flex)
                        alignItems(AlignItems.Center)
                        gap(6.px)
                        property("background", if (isLight) "rgba(60, 131, 239, 0.1)" else "rgba(60, 131, 239, 0.15)")
                        property("border-radius", "8px")
                        property("padding", "6px 12px")
                        property("font-size", "13px")
                        property("font-weight", "500")
                        property("color", highlightColor)
                    }
                }) {
                    SpanText(icon)
                    SpanText(label)
                }
            }
        }

        // Quote/tagline
        Div({
            style {
                property("font-size", "14px")
                property("font-style", "italic")
                property("color", subtleText)
                property("border-left", "3px solid $highlightColor")
                property("padding-left", "12px")
            }
        }) {
            SpanText("\"Building impactful digital solutions that drive business success — one line of Kotlin at a time.\"")
        }
    }
}

@Composable
private fun ExpertiseBadges() {
    val expertiseBadges = listOf(
        "📱 Android TechLead" to "#3C83EF",
        "🔄 KMP Developer" to "#7F52FF",
        "🌐 Kobweb" to "#10B981",
        "🚀 GoLang" to "#00ADD8",
        "📲 FullStack Mobile" to "#F59E0B"
    )

    Div({
        style {
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            gap(10.px)
            marginTop(20.px)
        }
    }) {
        expertiseBadges.forEach { (label, color) ->
            Div({
                style {
                    property("background", color)
                    property("border-radius", "20px")
                    property("padding", "8px 16px")
                    property("font-size", "13px")
                    property("font-weight", "600")
                    property("color", "white")
                    property("white-space", "nowrap")
                    property("transition", "all 0.3s ease")
                    property("cursor", "default")
                }
                onMouseEnter {
                    it.currentTarget.asDynamic().style.transform = "translateY(-2px)"
                    it.currentTarget.asDynamic().style.opacity = "0.9"
                }
                onMouseLeave {
                    it.currentTarget.asDynamic().style.transform = "translateY(0)"
                    it.currentTarget.asDynamic().style.opacity = "1"
                }
            }) {
                SpanText(label)
            }
        }
    }
}
