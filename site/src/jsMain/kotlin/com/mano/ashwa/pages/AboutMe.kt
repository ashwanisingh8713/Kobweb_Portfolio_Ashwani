package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.toSitePalette
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@InitRoute
fun initAboutMePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("About Me | Ashwani Kumar Singh"))
}

@Page("/aboutme")
@Layout(".components.layouts.PageLayout")
@Composable
fun AboutMe() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(sitePal.nearBackground)
            .padding(top = 80.px, leftRight = 16.px, bottom = 60.px),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().maxWidth(1000.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Page Header
            AboutPageHeader(sitePal)

            // Bio Section
            BioSection(sitePal, isLight)

            // Journey Section
            JourneySection(sitePal, isLight)

            // Values Section
            ValuesSection(sitePal, isLight)
        }
    }
}

@Composable
private fun AboutPageHeader(sitePal: com.mano.ashwa.SitePalette) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().padding(bottom = 48.px)
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

        SpanText(
            "About Me",
            modifier = Modifier
                .fontSize(36.px)
                .fontWeight(FontWeight.Bold)
                .color(sitePal.pageTitleColor)
                .margin(bottom = 12.px)
        )

        SpanText(
            "Get to know the person behind the code",
            modifier = Modifier
                .fontSize(18.px)
                .color(sitePal.textColor)
                .textAlign(TextAlign.Center)
        )
    }
}

@Composable
private fun BioSection(sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    val cardBg = sitePal.cardColor.toString()
    val borderColor = if (isLight) "rgba(0, 0, 0, 0.08)" else "rgba(255, 255, 255, 0.08)"

    Div({
        style {
            width(100.percent)
            property("background", cardBg)
            property("border-radius", "16px")
            property("padding", "32px")
            property("border", "1px solid $borderColor")
            property("margin-bottom", "32px")
            if (isLight) {
                property("box-shadow", "0 2px 12px rgba(0, 0, 0, 0.06)")
            }
        }
    }) {
        Column(modifier = Modifier.gap(20.px)) {
            SpanText(
                "Hello, I'm Ashwani Kumar Singh",
                modifier = Modifier
                    .fontSize(24.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.cardTitleColor)
            )

            SpanText(
                """I'm a self-taught software developer and Tech Lead with over 14 years of experience 
                in mobile and web development. My journey in tech started with a simple curiosity about 
                how apps work, and today I lead teams building enterprise-grade applications for global clients.""",
                modifier = Modifier
                    .fontSize(16.px)
                    .lineHeight(1.8)
                    .color(sitePal.cardDescriptionColor)
            )

            SpanText(
                """Currently, I'm working as a Tech Lead at Tech Mahindra, where I architect and develop 
                Android applications for Keysight's wireless network diagnostics tools. I specialize in 
                Android native development, Kotlin Multiplatform (KMP), and modern web technologies like Kobweb.""",
                modifier = Modifier
                    .fontSize(16.px)
                    .lineHeight(1.8)
                    .color(sitePal.cardDescriptionColor)
            )

            SpanText(
                """I believe in clean architecture, writing maintainable code, and continuous learning. 
                When I'm not coding, I enjoy exploring new technologies, contributing to open source, 
                and mentoring fellow developers.""",
                modifier = Modifier
                    .fontSize(16.px)
                    .lineHeight(1.8)
                    .color(sitePal.cardDescriptionColor)
            )
        }
    }
}

@Composable
private fun JourneySection(sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 32.px),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpanText(
            "My Journey",
            modifier = Modifier
                .fontSize(28.px)
                .fontWeight(FontWeight.Bold)
                .color(sitePal.pageTitleColor)
                .margin(bottom = 24.px)
        )

        SimpleGrid(
            numColumns(base = 1, md = 3),
            modifier = Modifier.fillMaxWidth().gap(20.px)
        ) {
            JourneyCard(
                year = "2011",
                title = "Started Coding",
                description = "Began my journey as a self-taught developer, learning Java and Android basics.",
                sitePal = sitePal,
                isLight = isLight
            )
            JourneyCard(
                year = "2015",
                title = "First Lead Role",
                description = "Took on my first team leadership role, managing Android projects for news media clients.",
                sitePal = sitePal,
                isLight = isLight
            )
            JourneyCard(
                year = "2024",
                title = "Tech Lead",
                description = "Currently leading enterprise Android development for telecom industry solutions.",
                sitePal = sitePal,
                isLight = isLight
            )
        }
    }
}

@Composable
private fun JourneyCard(year: String, title: String, description: String, sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    val cardBg = sitePal.cardColor.toString()
    val borderColor = if (isLight) "rgba(0, 0, 0, 0.08)" else "rgba(255, 255, 255, 0.08)"
    val accentColor = "#3C83EF"

    Div({
        style {
            property("background", cardBg)
            property("border-radius", "16px")
            property("padding", "24px")
            property("border", "1px solid $borderColor")
            property("transition", "all 0.3s ease")
            property("text-align", "center")
            if (isLight) {
                property("box-shadow", "0 2px 12px rgba(0, 0, 0, 0.06)")
            }
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateY(-4px)"
            it.currentTarget.asDynamic().style.borderColor = accentColor
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateY(0)"
            it.currentTarget.asDynamic().style.borderColor = borderColor
        }
    }) {
        Column(
            modifier = Modifier.gap(12.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText(
                year,
                modifier = Modifier
                    .fontSize(32.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.brand.primary)
            )
            SpanText(
                title,
                modifier = Modifier
                    .fontSize(18.px)
                    .fontWeight(FontWeight.SemiBold)
                    .color(sitePal.cardTitleColor)
            )
            SpanText(
                description,
                modifier = Modifier
                    .fontSize(14.px)
                    .lineHeight(1.6)
                    .color(sitePal.cardDescriptionColor)
            )
        }
    }
}

@Composable
private fun ValuesSection(sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpanText(
            "What I Believe In",
            modifier = Modifier
                .fontSize(28.px)
                .fontWeight(FontWeight.Bold)
                .color(sitePal.pageTitleColor)
                .margin(bottom = 24.px)
        )

        SimpleGrid(
            numColumns(base = 1, sm = 2),
            modifier = Modifier.fillMaxWidth().gap(20.px)
        ) {
            ValueCard("🎯", "Clean Code", "Writing readable, maintainable, and well-documented code that stands the test of time.", sitePal, isLight)
            ValueCard("📚", "Continuous Learning", "Staying updated with the latest technologies and best practices in software development.", sitePal, isLight)
            ValueCard("🤝", "Collaboration", "Working effectively with teams, sharing knowledge, and helping others grow.", sitePal, isLight)
            ValueCard("⚡", "Quality First", "Delivering high-quality solutions that exceed expectations and provide real value.", sitePal, isLight)
        }
    }
}

@Composable
private fun ValueCard(icon: String, title: String, description: String, sitePal: com.mano.ashwa.SitePalette, isLight: Boolean) {
    val cardBg = sitePal.cardColor.toString()
    val borderColor = if (isLight) "rgba(0, 0, 0, 0.08)" else "rgba(255, 255, 255, 0.08)"
    val accentColor = "#3C83EF"

    Div({
        style {
            property("background", cardBg)
            property("border-radius", "16px")
            property("padding", "24px")
            property("border", "1px solid $borderColor")
            property("transition", "all 0.3s ease")
            display(DisplayStyle.Flex)
            gap(16.px)
            alignItems(AlignItems.FlexStart)
            if (isLight) {
                property("box-shadow", "0 2px 12px rgba(0, 0, 0, 0.06)")
            }
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateY(-4px)"
            it.currentTarget.asDynamic().style.borderColor = accentColor
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateY(0)"
            it.currentTarget.asDynamic().style.borderColor = borderColor
        }
    }) {
        // Icon
        Div({
            style {
                property("background", "${accentColor}15")
                property("border-radius", "12px")
                property("padding", "12px")
                property("font-size", "24px")
                property("flex-shrink", "0")
            }
        }) {
            SpanText(icon)
        }

        // Content
        Column(modifier = Modifier.gap(8.px)) {
            SpanText(
                title,
                modifier = Modifier
                    .fontSize(18.px)
                    .fontWeight(FontWeight.SemiBold)
                    .color(sitePal.cardTitleColor)
            )
            SpanText(
                description,
                modifier = Modifier
                    .fontSize(14.px)
                    .lineHeight(1.6)
                    .color(sitePal.cardDescriptionColor)
            )
        }
    }
}
