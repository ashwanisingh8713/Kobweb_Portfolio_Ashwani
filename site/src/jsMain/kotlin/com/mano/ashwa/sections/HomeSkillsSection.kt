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

// Highlighted skills for homepage
private val homeSkillsData = listOf(
    HomeSkillItem(
        icon = "📱",
        title = "Android & KMP",
        description = "Expert in Android native development and Kotlin Multiplatform for cross-platform apps targeting Android, iOS, and Web.",
        technologies = listOf("Kotlin", "Jetpack Compose", "KMP", "Swift")
    ),
    HomeSkillItem(
        icon = "🏗️",
        title = "Architecture",
        description = "Designing scalable, maintainable apps using Clean Architecture, MVVM/MVI patterns, and SOLID principles.",
        technologies = listOf("Clean Arch", "MVVM", "MVI", "Modular")
    ),
    HomeSkillItem(
        icon = "🌐",
        title = "Web Development",
        description = "Building modern web applications with Kobweb, Compose for Web, and full-stack Kotlin solutions.",
        technologies = listOf("Kobweb", "Compose Web", "Ktor", "MongoDB")
    ),
    HomeSkillItem(
        icon = "☁️",
        title = "Backend & Cloud",
        description = "Creating RESTful APIs, microservices, and cloud integrations with Firebase and GoLang.",
        technologies = listOf("Firebase", "GoLang", "GraphQL", "REST")
    ),
    HomeSkillItem(
        icon = "👥",
        title = "Team Leadership",
        description = "Leading cross-functional teams, mentoring developers, and driving projects from concept to delivery.",
        technologies = listOf("Agile", "Scrum", "Code Review", "Mentoring")
    ),
    HomeSkillItem(
        icon = "🤖",
        title = "AI & Automation",
        description = "Leveraging AI tools, prompt engineering, and building intelligent features into applications.",
        technologies = listOf("AI Agents", "Prompt Eng", "Automation", "ML APIs")
    )
)

private data class HomeSkillItem(
    val icon: String,
    val title: String,
    val description: String,
    val technologies: List<String>
)

@Composable
fun HomeSkillsSection() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(sitePal.contactAreaBg)
            .padding(topBottom = 60.px, leftRight = 16.px) // Reduced padding for mobile
            .id("skills"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().maxWidth(1200.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Section Title
            SpanText(
                "What I Do",
                modifier = Modifier
                    .fontSize(36.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.pageTitleColor)
                    .margin(bottom = 16.px)
            )

            // Subtitle
            SpanText(
                "Specialized in mobile-first development with a full-stack mindset",
                modifier = Modifier
                    .fontSize(18.px)
                    .color(sitePal.textColor)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 48.px)
            )

            // Skills Grid
            SimpleGrid(
                numColumns(base = 1, sm = 2, lg = 3),
                modifier = Modifier.fillMaxWidth().gap(24.px)
            ) {
                homeSkillsData.forEach { skill ->
                    HomeSkillCard(skill, sitePal, isLight)
                }
            }
        }
    }
}

@Composable
private fun HomeSkillCard(skill: HomeSkillItem, sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
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
            property("padding", "28px")
            property("border", "1px solid $borderColor")
            property("transition", "all 0.3s ease")
            property("cursor", "default")
            property("height", "100%")
            if (isLight) {
                property("box-shadow", "0 4px 15px rgba(0, 0, 0, 0.08)")
            }
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateY(-8px)"
            it.currentTarget.asDynamic().style.boxShadow = "0 20px 40px rgba(60, 131, 239, 0.15)"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.4)"
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateY(0)"
            it.currentTarget.asDynamic().style.boxShadow = "none"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.15)"
        }
    }) {
        // Icon
        Div({
            style {
                property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.3), rgba(170, 54, 124, 0.3))")
                property("border-radius", "14px")
                property("padding", "14px")
                property("width", "fit-content")
                property("font-size", "32px")
                property("margin-bottom", "20px")
            }
        }) {
            SpanText(skill.icon)
        }

        // Title
        SpanText(
            skill.title,
            modifier = Modifier
                .fontSize(22.px)
                .fontWeight(FontWeight.Bold)
                .color(sitePal.cardTitleColor)
                .margin(bottom = 12.px)
        )

        // Description
        SpanText(
            skill.description,
            modifier = Modifier
                .fontSize(15.px)
                .lineHeight(1.6)
                .color(sitePal.cardDescriptionColor)
                .margin(bottom = 20.px)
        )

        // Tech tags
        Div({
            style {
                display(DisplayStyle.Flex)
                flexWrap(FlexWrap.Wrap)
                gap(8.px)
                property("margin-top", "auto")
            }
        }) {
            skill.technologies.forEach { tech ->
                Div({
                    style {
                        property("background", "rgba(60, 131, 239, 0.15)")
                        property("border-radius", "12px")
                        property("padding", "6px 12px")
                        property("font-size", "12px")
                        property("color", sitePal.chipTextColor.toString())
                        property("border", "1px solid rgba(125, 211, 252, 0.15)")
                    }
                }) {
                    SpanText(tech)
                }
            }
        }
    }
}

