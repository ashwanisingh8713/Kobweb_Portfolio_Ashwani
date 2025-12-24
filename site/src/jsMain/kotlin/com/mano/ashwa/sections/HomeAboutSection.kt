package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.toSitePalette
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Composable
fun HomeAboutSection() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(sitePal.nearBackground)
            .padding(topBottom = 60.px, leftRight = 16.px) // Reduced padding for mobile
            .id("about"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().maxWidth(1200.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section Title
            SpanText(
                "About Me",
                modifier = Modifier
                    .fontSize(36.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.pageTitleColor)
                    .margin(bottom = 16.px)
            )

            // Subtitle
            SpanText(
                "Passionate about building exceptional mobile & web experiences",
                modifier = Modifier
                    .fontSize(18.px)
                    .color(sitePal.textColor)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 48.px)
            )

            // Content Grid
            SimpleGrid(
                numColumns(base = 1, md = 2),
                modifier = Modifier.fillMaxWidth().gap(48.px)
            ) {
                // Left: Stats Cards
                Column(modifier = Modifier.gap(24.px)) {
                    StatCard(
                        number = "14+",
                        label = "Years of Experience",
                        icon = "💼",
                        sitePal = sitePal,
                        isLight = isLight
                    )
                    StatCard(
                        number = "50+",
                        label = "Projects Delivered",
                        icon = "🚀",
                        sitePal = sitePal,
                        isLight = isLight
                    )
                    StatCard(
                        number = "10+",
                        label = "Team Members Led",
                        icon = "👥",
                        sitePal = sitePal,
                        isLight = isLight
                    )
                }

                // Right: About Text
                Column(modifier = Modifier.gap(20.px)) {
                    SpanText(
                        "Who I Am",
                        modifier = Modifier
                            .fontSize(24.px)
                            .fontWeight(FontWeight.Bold)
                            .color(sitePal.cardTitleColor)
                    )

                    SpanText(
                        """I'm a self-taught software developer and Tech Lead with over 14 years of experience 
                        in mobile and web development. My journey started with a passion for creating 
                        impactful digital solutions, and today I specialize in Android development, 
                        Kotlin Multiplatform, and modern web technologies.""",
                        modifier = Modifier
                            .fontSize(16.px)
                            .lineHeight(1.8)
                            .color(sitePal.cardDescriptionColor)
                    )

                    SpanText(
                        """I believe in clean architecture, test-driven development, and continuous learning. 
                        Currently, I'm working as a Tech Lead at Tech Mahindra, where I lead teams to 
                        build enterprise-grade applications for global clients.""",
                        modifier = Modifier
                            .fontSize(16.px)
                            .lineHeight(1.8)
                            .color(sitePal.cardDescriptionColor)
                    )

                    // Tech Stack Pills
                    SpanText(
                        "Core Technologies",
                        modifier = Modifier
                            .fontSize(18.px)
                            .fontWeight(FontWeight.Bold)
                            .color(sitePal.cardTitleColor)
                            .margin(top = 16.px)
                    )

                    Div({
                        style {
                            display(DisplayStyle.Flex)
                            flexWrap(FlexWrap.Wrap)
                            gap(12.px)
                            marginTop(12.px)
                        }
                    }) {
                        listOf("Kotlin", "Android", "KMP", "Jetpack Compose", "Kobweb", "Ktor", "Firebase", "GoLang").forEach { tech ->
                            TechPill(tech, sitePal, isLight)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatCard(number: String, label: String, icon: String, sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    val cardBg = if (isLight) {
        "linear-gradient(135deg, ${sitePal.cardColor}, rgba(60, 131, 239, 0.05))"
    } else {
        "linear-gradient(135deg, ${sitePal.cardColor}, rgba(60, 131, 239, 0.1))"
    }
    val borderColor = if (isLight) "rgba(60, 131, 239, 0.15)" else "rgba(60, 131, 239, 0.2)"

    Div({
        style {
            display(DisplayStyle.Flex)
            alignItems(AlignItems.Center)
            gap(20.px)
            padding(24.px)
            property("background", cardBg)
            property("border-radius", "16px")
            property("border", "1px solid $borderColor")
            property("transition", "all 0.3s ease")
            if (isLight) {
                property("box-shadow", "0 4px 15px rgba(0, 0, 0, 0.06)")
            }
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateX(8px)"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.5)"
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateX(0)"
            it.currentTarget.asDynamic().style.borderColor = borderColor
        }
    }) {
        // Icon
        Div({
            style {
                property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.3), rgba(170, 54, 124, 0.3))")
                property("border-radius", "12px")
                property("padding", "16px")
                property("font-size", "32px")
            }
        }) {
            SpanText(icon)
        }

        // Text
        Column(modifier = Modifier.gap(4.px)) {
            SpanText(
                number,
                modifier = Modifier
                    .fontSize(32.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.brand.primary)
            )
            SpanText(
                label,
                modifier = Modifier
                    .fontSize(14.px)
                    .color(sitePal.cardSubTitleColor)
            )
        }
    }
}

@Composable
private fun TechPill(text: String, sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    val pillBg = if (isLight) {
        "linear-gradient(135deg, ${sitePal.chipColor}, rgba(60, 131, 239, 0.1))"
    } else {
        "linear-gradient(135deg, ${sitePal.chipColor}, rgba(60, 131, 239, 0.2))"
    }
    val borderColor = if (isLight) "rgba(60, 131, 239, 0.2)" else "rgba(125, 211, 252, 0.2)"

    Div({
        style {
            property("background", pillBg)
            property("border-radius", "20px")
            property("padding", "8px 16px")
            property("font-size", "14px")
            property("font-weight", "500")
            property("color", sitePal.chipTextColor.toString())
            property("border", "1px solid $borderColor")
        }
    }) {
        SpanText(text)
    }
}

