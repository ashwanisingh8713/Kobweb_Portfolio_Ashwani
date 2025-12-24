package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.toSitePalette
import com.mano.ashwa.components.widgets.ChipLayout
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

// Featured projects for homepage
private val featuredProjects = listOf(
    FeaturedProject(
        icon = "📱",
        name = "Nemo Handy",
        role = "Tech Lead / Android Architect",
        description = "Enterprise Android app for wireless network diagnostics, QoS/QoE testing, and real-time monitoring for 5G/LTE networks.",
        technologies = listOf("Android", "Kotlin", "JNI", "Bluetooth", "REST API"),
        highlight = "Featured"
    ),
    FeaturedProject(
        icon = "🌐",
        name = "Kobweb Portfolio",
        role = "Full Stack Developer",
        description = "Modern portfolio website built entirely in Kotlin using Kobweb framework with Compose for Web and responsive design.",
        technologies = listOf("Kobweb", "Compose Web", "Kotlin/JS"),
        highlight = "Open Source"
    ),
    FeaturedProject(
        icon = "📰",
        name = "THE HINDU KMP",
        role = "Senior Developer",
        description = "Cross-platform news app using Kotlin Multiplatform targeting Android, iOS with shared business logic and native UI.",
        technologies = listOf("KMP", "Compose", "Ktor", "SQLDelight", "GraphQL"),
        highlight = "In Progress"
    )
)

private data class FeaturedProject(
    val icon: String,
    val name: String,
    val role: String,
    val description: String,
    val technologies: List<String>,
    val highlight: String
)

@Composable
fun HomeFeaturedProjectsSection() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(sitePal.nearBackground)
            .padding(topBottom = 80.px, leftRight = 40.px)
            .id("projects"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().maxWidth(1200.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section Title
            SpanText(
                "Featured Projects",
                modifier = Modifier
                    .fontSize(36.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.pageTitleColor)
                    .margin(bottom = 16.px)
            )

            // Subtitle
            SpanText(
                "A selection of projects that showcase my expertise",
                modifier = Modifier
                    .fontSize(18.px)
                    .color(sitePal.textColor)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 48.px)
            )

            // Projects Grid
            SimpleGrid(
                numColumns(base = 1, md = 2, lg = 3),
                modifier = Modifier.fillMaxWidth().gap(24.px)
            ) {
                featuredProjects.forEach { project ->
                    FeaturedProjectCard(project, sitePal, isLight)
                }
            }

            // View All Link
            Div({
                style {
                    marginTop(48.px)
                    property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.2), rgba(170, 54, 124, 0.2))")
                    property("border-radius", "30px")
                    property("padding", "14px 32px")
                    property("cursor", "pointer")
                    property("transition", "all 0.3s ease")
                    property("border", "1px solid rgba(60, 131, 239, 0.3)")
                }
                onMouseEnter {
                    it.currentTarget.asDynamic().style.background = "linear-gradient(135deg, rgba(60, 131, 239, 0.4), rgba(170, 54, 124, 0.4))"
                    it.currentTarget.asDynamic().style.transform = "scale(1.05)"
                }
                onMouseLeave {
                    it.currentTarget.asDynamic().style.background = "linear-gradient(135deg, rgba(60, 131, 239, 0.2), rgba(170, 54, 124, 0.2))"
                    it.currentTarget.asDynamic().style.transform = "scale(1)"
                }
                onClick {
                    kotlinx.browser.window.location.href = "/project"
                }
            }) {
                SpanText(
                    "View All Projects →",
                    modifier = Modifier
                        .fontSize(16.px)
                        .fontWeight(FontWeight.Bold)
                        .color(sitePal.chipTextColor)
                )
            }
        }
    }
}

@Composable
private fun FeaturedProjectCard(project: FeaturedProject, sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    val cardBg = if (isLight) {
        "linear-gradient(145deg, ${sitePal.cardColor}, rgba(248, 250, 252, 0.95))"
    } else {
        "linear-gradient(145deg, ${sitePal.cardColor}, rgba(11, 18, 32, 0.95))"
    }
    val borderColor = if (isLight) "rgba(60, 131, 239, 0.2)" else "rgba(60, 131, 239, 0.15)"

    Div({
        style {
            property("background", cardBg)
            property("border-radius", "16px")
            property("padding", "24px")
            property("border", "1px solid $borderColor")
            property("transition", "all 0.3s ease")
            property("cursor", "default")
            property("position", "relative")
            property("overflow", "hidden")
            if (isLight) {
                property("box-shadow", "0 4px 15px rgba(0, 0, 0, 0.08)")
            }
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            property("height", "100%")
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateY(-8px)"
            it.currentTarget.asDynamic().style.boxShadow = "0 20px 40px rgba(60, 131, 239, 0.15)"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.4)"
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateY(0)"
            it.currentTarget.asDynamic().style.boxShadow = if (isLight) "0 4px 15px rgba(0, 0, 0, 0.08)" else "none"
            it.currentTarget.asDynamic().style.borderColor = borderColor
        }
    }) {
        // Highlight Badge
        Div({
            style {
                property("position", "absolute")
                property("top", "16px")
                property("right", "16px")
                property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.8), rgba(170, 54, 124, 0.8))")
                property("border-radius", "12px")
                property("padding", "4px 12px")
                property("font-size", "11px")
                property("font-weight", "600")
                property("color", "white")
                property("text-transform", "uppercase")
                property("letter-spacing", "0.5px")
            }
        }) {
            SpanText(project.highlight)
        }

        // Icon
        Div({
            style {
                property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.3), rgba(170, 54, 124, 0.3))")
                property("border-radius", "14px")
                property("padding", "14px")
                property("width", "fit-content")
                property("font-size", "32px")
                property("margin-bottom", "16px")
            }
        }) {
            SpanText(project.icon)
        }

        // Name
        SpanText(
            project.name,
            modifier = Modifier
                .fontSize(22.px)
                .fontWeight(FontWeight.Bold)
                .color(sitePal.cardTitleColor)
                .margin(bottom = 4.px)
        )

        // Role
        SpanText(
            project.role,
            modifier = Modifier
                .fontSize(14.px)
                .fontWeight(FontWeight.Medium)
                .color(sitePal.brand.primary)
                .margin(bottom = 12.px)
        )

        // Description
        SpanText(
            project.description,
            modifier = Modifier
                .fontSize(14.px)
                .lineHeight(1.6)
                .color(sitePal.cardDescriptionColor)
                .margin(bottom = 20.px)
        )

        // Technologies
        Div({
            style {
                display(DisplayStyle.Flex)
                flexWrap(FlexWrap.Wrap)
                gap(8.px)
                property("margin-top", "auto")
            }
        }) {
            project.technologies.forEach { tech ->
                Div({
                    style {
                        property("background", sitePal.chipColor.toString())
                        property("border-radius", "12px")
                        property("padding", "6px 12px")
                        property("font-size", "12px")
                        property("color", sitePal.chipTextColor.toString())
                    }
                }) {
                    SpanText(tech)
                }
            }
        }
    }
}

