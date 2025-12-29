package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.toSitePalette
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

// Expertise areas data
private val expertiseAreas = listOf(
    ExpertiseItem(
        icon = "📱",
        title = "Android Tech Lead",
        description = "Leading teams to build enterprise-grade Android apps with clean architecture, Jetpack Compose, and modern development practices.",
        accentColor = "#3C83EF"
    ),
    ExpertiseItem(
        icon = "🔄",
        title = "Kotlin Multiplatform Developer",
        description = "Building cross-platform apps targeting Android, iOS & Web with shared business logic using KMP, Compose Multiplatform.",
        accentColor = "#7F52FF"
    ),
    ExpertiseItem(
        icon = "🌐",
        title = "Kobweb Developer",
        description = "Creating modern, responsive web applications entirely in Kotlin using Kobweb framework with Compose for Web.",
        accentColor = "#10B981"
    ),
    ExpertiseItem(
        icon = "🚀",
        title = "GoLang Developer",
        description = "Developing high-performance backend services, REST APIs, and microservices using Go for scalable solutions.",
        accentColor = "#00ADD8"
    ),
    ExpertiseItem(
        icon = "📲",
        title = "FullStack Mobile Developer",
        description = "End-to-end mobile development expertise from UI/UX to backend integration, cloud services, and deployment.",
        accentColor = "#F59E0B"
    )
)

private data class ExpertiseItem(
    val icon: String,
    val title: String,
    val description: String,
    val accentColor: String
)

@Composable
fun HomeExpertiseSection() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(sitePal.contactAreaBg)
            .padding(topBottom = 80.px, leftRight = 16.px)
            .id("expertise"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().maxWidth(1200.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Decorative line
            Div({
                style {
                    width(60.px)
                    height(4.px)
                    property("background", "#3C83EF")
                    property("border-radius", "2px")
                    property("margin-bottom", "20px")
                }
            })

            // Section Title
            SpanText(
                "What I Specialize In",
                modifier = Modifier
                    .fontSize(36.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.pageTitleColor)
                    .margin(bottom = 12.px)
            )

            // Subtitle
            SpanText(
                "Expertise across mobile, web, and backend technologies",
                modifier = Modifier
                    .fontSize(18.px)
                    .color(sitePal.textColor)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 56.px)
            )

            // Expertise Grid - 5 cards with responsive layout
            SimpleGrid(
                numColumns(base = 1, sm = 2, lg = 3),
                modifier = Modifier.fillMaxWidth().gap(24.px)
            ) {
                expertiseAreas.forEach { expertise ->
                    ExpertiseCard(expertise, sitePal, isLight)
                }
            }
        }
    }
}

@Composable
private fun ExpertiseCard(expertise: ExpertiseItem, sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    val cardBg = sitePal.cardColor.toString()
    val borderColor = if (isLight) "rgba(0, 0, 0, 0.08)" else "rgba(255, 255, 255, 0.08)"
    val accentColor = expertise.accentColor

    Div({
        style {
            property("background", cardBg)
            property("border-radius", "16px")
            property("padding", "28px")
            property("border", "1px solid $borderColor")
            property("transition", "all 0.3s ease")
            property("cursor", "default")
            property("position", "relative")
            property("overflow", "hidden")
            if (isLight) {
                property("box-shadow", "0 2px 12px rgba(0, 0, 0, 0.06)")
            }
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            property("height", "100%")
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateY(-8px)"
            it.currentTarget.asDynamic().style.boxShadow = "0 16px 40px rgba(0, 0, 0, 0.12)"
            it.currentTarget.asDynamic().style.borderColor = accentColor
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateY(0)"
            it.currentTarget.asDynamic().style.boxShadow = if (isLight) "0 2px 12px rgba(0, 0, 0, 0.06)" else "none"
            it.currentTarget.asDynamic().style.borderColor = borderColor
        }
    }) {
        // Accent line at top
        Div({
            style {
                property("position", "absolute")
                property("top", "0")
                property("left", "0")
                property("right", "0")
                property("height", "3px")
                property("background", accentColor)
            }
        })

        // Icon with subtle background
        Div({
            style {
                property("background", "${accentColor}15")
                property("border-radius", "12px")
                property("padding", "14px")
                property("width", "fit-content")
                property("font-size", "32px")
                property("margin-bottom", "20px")
            }
        }) {
            SpanText(expertise.icon)
        }

        // Title
        SpanText(
            expertise.title,
            modifier = Modifier
                .fontSize(20.px)
                .fontWeight(FontWeight.Bold)
                .color(sitePal.cardTitleColor)
                .margin(bottom = 12.px)
        )

        // Description
        SpanText(
            expertise.description,
            modifier = Modifier
                .fontSize(15.px)
                .lineHeight(1.7)
                .color(sitePal.cardDescriptionColor)
        )
    }
}

