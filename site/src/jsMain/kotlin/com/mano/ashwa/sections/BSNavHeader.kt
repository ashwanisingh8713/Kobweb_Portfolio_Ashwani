package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.ThemeToggle
import com.mano.ashwa.navigation.Screen
import com.mano.ashwa.toSitePalette
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLAnchorElement

// Navigation items using correct Screen references
private val navItems = listOf(
    NavItem("Home", Screen.Home.route, Screen.Home.id),
    NavItem("Skills", Screen.Skill.route, Screen.Skill.id),
    NavItem("Projects", Screen.Project.route, Screen.Project.id),
    NavItem("Experience", Screen.Experience.route, Screen.Experience.id),
)

private data class NavItem(val label: String, val path: String, val id: String)

// CSS Styles using Kobweb's CssStyle with breakpoints
val NavLinksContainerStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.None)
    }
    Breakpoint.MD {
        Modifier.display(DisplayStyle.Flex)
    }
}

val BrandNameStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.None)
    }
    Breakpoint.SM {
        Modifier.display(DisplayStyle.Block)
    }
}

val ResumeTextStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.None)
    }
    Breakpoint.SM {
        Modifier.display(DisplayStyle.Block)
    }
}

@Composable
fun BSHeader(ctx: PageContext) {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    var hoveredItem by remember { mutableStateOf<String?>(null) }

    // Theme-aware colors
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT
    val headerBgColor = if (isLight) "rgba(255, 255, 255, 0.9)" else "rgba(6, 8, 11, 0.85)"
    val borderColor = if (isLight) "rgba(60, 131, 239, 0.2)" else "rgba(60, 131, 239, 0.15)"
    val textColor = if (isLight) Colors.Black else Colors.White

    // Get current path to highlight active nav item
    val currentPath = ctx.route.path

    Div({
        style {
            width(100.percent)
            position(Position.Fixed)
            top(0.px)
            left(0.px)
            property("z-index", "1000")
            padding(16.px, 40.px)
            property("backdrop-filter", "blur(20px)")
            property("-webkit-backdrop-filter", "blur(20px)")
            property("background", headerBgColor)
            property("border-bottom", "1px solid $borderColor")
            property("box-shadow", if (isLight) "0 2px 20px rgba(0, 0, 0, 0.08)" else "0 4px 30px rgba(0, 0, 0, 0.3)")
        }
    }) {
        Row(
            modifier = Modifier.fillMaxWidth().maxWidth(1400.px),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo / Brand
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Logo icon
                Div({
                    style {
                        property("background", "linear-gradient(135deg, #3C83EF, #7F52FF)")
                        property("border-radius", "12px")
                        property("padding", "8px 12px")
                        property("margin-right", "12px")
                        property("cursor", "pointer")
                    }
                    onClick { window.location.href = "/" }
                }) {
                    SpanText(
                        "AK",
                        modifier = Modifier
                            .fontSize(18.px)
                            .fontWeight(FontWeight.Bold)
                            .color(Colors.White)
                    )
                }

                // Brand name (hidden on mobile using CssStyle)
                Box(modifier = BrandNameStyle.toModifier().onClick { window.location.href = "/" }) {
                    SpanText(
                        "Ashwani Kumar",
                        modifier = Modifier
                            .fontSize(20.px)
                            .fontWeight(FontWeight.Bold)
                            .color(textColor)
                    )
                }
            }

            // Navigation Links (hidden on mobile using CssStyle)
            Row(
                modifier = NavLinksContainerStyle.toModifier().gap(8.px),
                verticalAlignment = Alignment.CenterVertically
            ) {
                navItems.forEach { item ->
                    val isActive = currentPath == item.path ||
                        (item.path == "/" && currentPath.isEmpty())
                    val isHovered = hoveredItem == item.id

                    val navHoverBg = if (isLight) "rgba(60, 131, 239, 0.08)" else "rgba(60, 131, 239, 0.1)"

                    Div({
                        style {
                            property("padding", "10px 20px")
                            property("border-radius", "25px")
                            property("cursor", "pointer")
                            property("transition", "all 0.3s ease")
                            property("position", "relative")

                            if (isActive) {
                                property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.25), rgba(127, 82, 255, 0.25))")
                                property("border", "1px solid rgba(60, 131, 239, 0.4)")
                            } else if (isHovered) {
                                property("background", "rgba(60, 131, 239, 0.1)")
                            }
                        }
                        onMouseEnter { hoveredItem = item.id }
                        onMouseLeave { hoveredItem = null }
                        onClick { window.location.href = item.path }
                    }) {
                        SpanText(
                            item.label,
                            modifier = Modifier
                                .fontSize(15.px)
                                .fontWeight(if (isActive) FontWeight.SemiBold else FontWeight.Normal)
                                .color(if (isActive) sitePal.brand.primary else textColor)
                        )
                    }
                }
            }

            // Right side: Theme Toggle + Download Button
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                // Theme Toggle
                Box(modifier = Modifier.margin(right = 16.px)) {
                    ThemeToggle(compact = true)
                }

                // Download Resume Button
                Div({
                    style {
                        property("background", "linear-gradient(135deg, #3C83EF, #7F52FF)")
                        property("border-radius", "25px")
                        property("padding", "10px 24px")
                        property("cursor", "pointer")
                        property("transition", "all 0.3s ease")
                        property("border", "none")
                        property("box-shadow", "0 4px 15px rgba(60, 131, 239, 0.3)")
                        display(DisplayStyle.Flex)
                        alignItems(AlignItems.Center)
                        gap(8.px)
                    }
                    onMouseEnter {
                        it.currentTarget.asDynamic().style.transform = "translateY(-2px)"
                        it.currentTarget.asDynamic().style.boxShadow = "0 6px 20px rgba(60, 131, 239, 0.4)"
                    }
                    onMouseLeave {
                        it.currentTarget.asDynamic().style.transform = "translateY(0)"
                        it.currentTarget.asDynamic().style.boxShadow = "0 4px 15px rgba(60, 131, 239, 0.3)"
                    }
                    onClick { downloadResume() }
                }) {
                    // Download icon
                    SpanText(
                        "📄",
                        modifier = Modifier.fontSize(14.px)
                    )

                    // Button text (hidden on small screens using CssStyle)
                    Box(modifier = ResumeTextStyle.toModifier()) {
                        SpanText(
                            "Resume",
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontWeight(FontWeight.SemiBold)
                                .color(Colors.White)
                        )
                    }
                }
            }
        }
    }
}

// Helper function to programmatically download the resume PDF from the public folder.
private fun downloadResume() {
    val fileName = "Ashwani TechLead & KMP Developer.pdf"
    val encoded = fileName
        .replace(" ", "%20")
        .replace("&", "%26")
    val href = "/resume/$encoded"

    val a = document.createElement("a") as HTMLAnchorElement
    a.href = href
    a.setAttribute("download", fileName)
    a.style.display = "none"
    document.body?.appendChild(a)
    a.click()
    a.remove()
}
