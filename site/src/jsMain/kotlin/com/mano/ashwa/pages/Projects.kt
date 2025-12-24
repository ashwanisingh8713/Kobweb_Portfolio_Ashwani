package com.mano.ashwa.pages

import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.navigation.Project_Route
import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText
import com.mano.ashwa.components.widgets.ProjectCardView
import com.mano.ashwa.model.ProjectData
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.padding
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.gap
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.toSitePalette

@InitRoute
fun initMyProjectsPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Portfolio & Projects | Ashwani Kumar Singh"))
}

@Page(Project_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun Projects() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    Box(Modifier.fillMaxWidth().backgroundColor(sitePal.nearBackground).padding(top = 80.px, leftRight = 16.px, bottom = 32.px)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Enhanced Page Title Section
            ProjectsPageTitleSection(sitePal)

            // Responsive grid: 1 column on mobile, 2 on larger screens
            Div({
                style {
                    width(100.percent)
                    display(DisplayStyle.Grid)
                    property("grid-template-columns", "repeat(auto-fit, minmax(300.px, 1fr))")
                    gap(24.px)
                    property("max-width", "1200px")
                }
            }) {
                projectCards.forEach { card ->
                    ProjectCardView(card)
                }
            }
        }
    }
}

@Composable
private fun ProjectsPageTitleSection(sitePal: com.mano.ashwa.SitePalette) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(bottom = 40.px)
    ) {
        // Decorative line
        Div({
            style {
                width(60.px)
                height(4.px)
                property("background", "linear-gradient(90deg, #3C83EF, #7F52FF)")
                property("border-radius", "2px")
                property("margin-bottom", "16px")
            }
        })

        // Title
        SpanText(
            "My Projects",
            modifier = Modifier
                .fontSize(32.px)
                .fontWeight(FontWeight.Bold)
                .color(sitePal.pageTitleColor)
                .padding(bottom = 8.px)
        )

        // Subtitle
        SpanText(
            "A showcase of my work and contributions",
            modifier = Modifier
                .fontSize(16.px)
                .color(sitePal.textColor)
        )
    }
}

private val projectCards = listOf(
    ProjectData(
        name = "Nemo Handy – Wireless Network Measurement Tool",
        description = "Android app for wireless diagnostics, QoS/QoE, and network performance. Features Bluetooth & GooglePlayService NearBy, 5G/LTE/VoLTE/IoT testing, dashboards, device compatibility, instant reporting, and cloud uploads.",
        duration = "2024 - Present",
        role = "Tech Lead / Android Architect",
        technologies = listOf("Android", "Kotlin", "JNI", "Bluetooth", "Google Play Service NearBy", "REST API", "Cloud Integration"),
        icon = "📱",
        color = Colors.LightBlue
    ),
    ProjectData(
        name = "Kobweb-Blog – Full-Stack Technical Blogging Platform",
        description = "Kotlin Multiplatform blogging platform using Kobweb, Compose for Web, Ktor, and MongoDB. Features category, search, auth, REST APIs, and dynamic content.",
        duration = "2023 - 2024",
        role = "Full Stack Developer",
        technologies = listOf("Kotlin Multiplatform", "Kobweb", "Compose for Web", "Ktor", "MongoDB"),
        icon = "🌐",
        color = Colors.LightGreen
    ),
    ProjectData(
        name = "Portfolio Website – Personal Branding Site",
        description = "Responsive portfolio site with contact form, built and deployed using Kobweb and Compose for Web.",
        duration = "2023 - Present",
        role = "Web Developer",
        technologies = listOf("Kotlin Multiplatform", "Kobweb", "Compose for Web", "Ktor", "MongoDB"),
        icon = "💼",
        color = Colors.LightYellow
    ),
    ProjectData(
        name = "THE HINDU: LIVE NEWS UPDATES & BUSINESS LINE",
        description = "Android news apps for The Hindu and Business Line. Features: customizable news feed, offline reading, multimedia, dynamic UIs, analytics, ads, and more.",
        duration = "2015 - 2024",
        role = "Lead Android Developer",
        technologies = listOf("Android", "Kotlin", "Java", "Dynamic UI", "DFP Ads", "Analytics", "REST API"),
        icon = "📰",
        color = Colors.LightCyan
    ),
    ProjectData(
        name = "KMP Project: Shopify Mobile Apps POC",
        description = "Kotlin Multiplatform POC for Shopify apps. Used Voyager Navigator, Koin, Ktor, Clean Arch, MVI, Material-3, Compose UI, GraphQL, and Shopify APIs.",
        duration = "2023 - 2024",
        role = "KMP Developer",
        technologies = listOf("Kotlin Multiplatform", "Compose UI", "Koin", "Ktor", "GraphQL", "Shopify API"),
        icon = "🛒",
        color = Colors.LightPink
    ),
    ProjectData(
        name = "Additional Projects",
        description = "Contributed to 10+ Android, Kotlin Multiplatform, and internal tooling projects.",
        duration = "2010 - Present",
        role = "Developer / Tech Lead",
        technologies = listOf("Android", "Kotlin", "KMP", "Internal Tools"),
        icon = "✨",
        color = Colors.WhiteSmoke
    )
)
