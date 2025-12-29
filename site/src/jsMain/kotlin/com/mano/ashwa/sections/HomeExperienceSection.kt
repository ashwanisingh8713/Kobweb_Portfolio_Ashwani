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
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

// Experience timeline data
private val experienceTimeline = listOf(
    TimelineItem(
        company = "Tech Mahindra",
        role = "Tech Lead",
        duration = "May 2024 - Present",
        description = "Leading Android development for Keysight's enterprise networking tools. Architecting solutions for 5G/LTE network diagnostics.",
        isCurrent = true
    ),
    TimelineItem(
        company = "Ninestars Technologies",
        role = "Lead Android Developer",
        duration = "Aug 2015 - May 2024",
        description = "Led development of multiple Android and KMP projects including news apps, e-commerce solutions, and enterprise applications.",
        isCurrent = false
    ),
    TimelineItem(
        company = "GlobalLogic India",
        role = "Senior Software Developer",
        duration = "Mar 2014 - Aug 2015",
        description = "Developed Android applications for global clients, focusing on performance optimization and clean architecture.",
        isCurrent = false
    )
)

private data class TimelineItem(
    val company: String,
    val role: String,
    val duration: String,
    val description: String,
    val isCurrent: Boolean
)

@Composable
fun HomeExperienceSection() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(sitePal.contactAreaBg)
            .padding(topBottom = 80.px, leftRight = 16.px)
            .id("experience"),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().maxWidth(900.px),
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
                "Experience",
                modifier = Modifier
                    .fontSize(36.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.pageTitleColor)
                    .margin(bottom = 12.px)
            )

            // Subtitle
            SpanText(
                "My professional journey in software development",
                modifier = Modifier
                    .fontSize(18.px)
                    .color(sitePal.textColor)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 56.px)
            )

            // Timeline
            Column(modifier = Modifier.fillMaxWidth().gap(0.px)) {
                experienceTimeline.forEachIndexed { index, item ->
                    TimelineCard(item, sitePal, isLast = index == experienceTimeline.size - 1, isLight = isLight)
                }
            }

            // View All Link
            Div({
                style {
                    marginTop(48.px)
                    property("background", "#3C83EF")
                    property("border-radius", "30px")
                    property("padding", "14px 32px")
                    property("cursor", "pointer")
                    property("transition", "all 0.3s ease")
                }
                onMouseEnter {
                    it.currentTarget.asDynamic().style.opacity = "0.9"
                    it.currentTarget.asDynamic().style.transform = "translateY(-2px)"
                }
                onMouseLeave {
                    it.currentTarget.asDynamic().style.opacity = "1"
                    it.currentTarget.asDynamic().style.transform = "translateY(0)"
                }
                onClick {
                    kotlinx.browser.window.location.href = "/experiences"
                }
            }) {
                SpanText(
                    "View Full Experience →",
                    modifier = Modifier
                        .fontSize(16.px)
                        .fontWeight(FontWeight.SemiBold)
                        .color(com.varabyte.kobweb.compose.ui.graphics.Colors.White)
                )
            }
        }
    }
}

@Composable
private fun TimelineCard(item: TimelineItem, sitePal: com.mano.ashwa.SitePalette, isLast: Boolean, isLight: Boolean) {
    val cardBg = sitePal.cardColor.toString()
    val borderColor = if (isLight) "rgba(0, 0, 0, 0.08)" else "rgba(255, 255, 255, 0.08)"
    val accentColor = "#3C83EF"

    Div({
        style {
            display(DisplayStyle.Flex)
            width(100.percent)
        }
    }) {
        // Timeline line and dot
        Div({
            style {
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Center)
                marginRight(24.px)
            }
        }) {
            // Dot
            Div({
                style {
                    width(16.px)
                    height(16.px)
                    property("border-radius", "50%")
                    if (item.isCurrent) {
                        property("background", accentColor)
                        property("box-shadow", "0 0 12px rgba(60, 131, 239, 0.5)")
                    } else {
                        property("background", "rgba(60, 131, 239, 0.4)")
                    }
                    property("border", "3px solid ${sitePal.contactAreaBg}")
                    property("z-index", "1")
                }
            })

            // Line
            if (!isLast) {
                Div({
                    style {
                        width(2.px)
                        flexGrow(1)
                        property("background", "rgba(60, 131, 239, 0.3)")
                        marginTop((-2).px)
                    }
                })
            }
        }

        // Content card
        Div({
            style {
                flexGrow(1)
                property("background", cardBg)
                property("border-radius", "16px")
                property("padding", "24px")
                property("border", "1px solid $borderColor")
                property("margin-bottom", "24px")
                property("transition", "all 0.3s ease")
                if (isLight) {
                    property("box-shadow", "0 2px 12px rgba(0, 0, 0, 0.06)")
                }
            }
            onMouseEnter {
                it.currentTarget.asDynamic().style.borderColor = accentColor
                it.currentTarget.asDynamic().style.transform = "translateX(8px)"
            }
            onMouseLeave {
                it.currentTarget.asDynamic().style.borderColor = borderColor
                it.currentTarget.asDynamic().style.transform = "translateX(0)"
            }
        }) {
            // Current badge
            if (item.isCurrent) {
                Div({
                    style {
                        property("display", "inline-block")
                        property("background", accentColor)
                        property("border-radius", "12px")
                        property("padding", "4px 12px")
                        property("font-size", "11px")
                        property("font-weight", "600")
                        property("color", "white")
                        property("text-transform", "uppercase")
                        property("letter-spacing", "0.5px")
                        property("margin-bottom", "12px")
                    }
                }) {
                    SpanText("Current")
                }
            }

            // Company
            SpanText(
                item.company,
                modifier = Modifier
                    .fontSize(22.px)
                    .fontWeight(FontWeight.Bold)
                    .color(sitePal.cardTitleColor)
            )

            // Role
            SpanText(
                item.role,
                modifier = Modifier
                    .fontSize(16.px)
                    .fontWeight(FontWeight.Medium)
                    .color(sitePal.brand.primary)
                    .display(DisplayStyle.Block)
                    .margin(top = 4.px, bottom = 8.px)
            )

            // Duration
            SpanText(
                item.duration,
                modifier = Modifier
                    .fontSize(14.px)
                    .color(sitePal.cardSubTitleColor)
                    .display(DisplayStyle.Block)
                    .margin(bottom = 12.px)
            )

            // Description
            SpanText(
                item.description,
                modifier = Modifier
                    .fontSize(14.px)
                    .lineHeight(1.6)
                    .color(sitePal.cardDescriptionColor)
            )
        }
    }
}

