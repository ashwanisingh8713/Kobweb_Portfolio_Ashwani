package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import org.w3c.dom.events.Event

// Navigation items using correct Screen references
private val navItems = listOf(
    NavItem("Home", Screen.Home.route, Screen.Home.id),
    NavItem("Skills", Screen.Skill.route, Screen.Skill.id),
    NavItem("Projects", Screen.Project.route, Screen.Project.id),
    NavItem("Experience", Screen.Experience.route, Screen.Experience.id),
    NavItem("Cover Letter", Screen.CoverLetter.route, Screen.CoverLetter.id),
)

private data class NavItem(val label: String, val path: String, val id: String)

// CSS Styles using Kobweb's CssStyle with breakpoints
// Desktop: >= 992px (LG) - Full navigation visible
// Tablet: 768px - 991px (MD) - Compact navigation or hamburger
// Mobile: < 768px (SM) - Hamburger menu only

// Note: Main responsive styles are defined in AppStyles.kt via CSS classes:
// - .mobile-menu-btn: visible on mobile/tablet, hidden on desktop
// - .desktop-nav-links: hidden on mobile/tablet, visible on desktop
// - .mobile-menu-dropdown: visible on mobile/tablet, hidden on desktop
// - .mobile-menu-backdrop: visible on mobile/tablet, hidden on desktop

val BrandNameStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.None) // Small mobile: hidden
    }
    Breakpoint.SM {
        Modifier.display(DisplayStyle.Block) // Larger mobile & up: visible
    }
}

val ResumeTextStyle = CssStyle {
    base {
        Modifier.display(DisplayStyle.None) // Mobile: hidden (icon only)
    }
    Breakpoint.SM {
        Modifier.display(DisplayStyle.Block) // Tablet & Desktop: visible
    }
}

// Brand name font size responsive
val BrandTextStyle = CssStyle {
    base {
        Modifier.fontSize(16.px) // Tablet
    }
    Breakpoint.MD {
        Modifier.fontSize(18.px) // Small Desktop
    }
    Breakpoint.LG {
        Modifier.fontSize(20.px) // Large Desktop
    }
}

// Note: Mobile/desktop visibility is now controlled via JavaScript window.innerWidth detection
// in BSHeader() for reliable first-load behavior. The following CssStyles are kept as fallback
// but the main responsive logic uses isDesktop state variable.


@Composable
fun BSHeader(ctx: PageContext) {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    var hoveredItem by remember { mutableStateOf<String?>(null) }
    var mobileMenuOpen by remember { mutableStateOf(false) }

    // Desktop breakpoint threshold (LG = 992px)
    val desktopBreakpoint = 992

    // Track window width for responsive behavior - initialize with current width
    var isDesktop by remember {
        mutableStateOf(
            try { window.innerWidth >= desktopBreakpoint } catch (_: Exception) { false }
        )
    }

    // Listen for window resize events to update responsive state
    DisposableEffect(Unit) {
        val resizeHandler: (Event) -> Unit = {
            isDesktop = window.innerWidth >= desktopBreakpoint
            // Close mobile menu when switching to desktop
            if (isDesktop) {
                mobileMenuOpen = false
            }
        }
        window.addEventListener("resize", resizeHandler)
        // Also trigger once on mount to ensure correct initial state
        isDesktop = try { window.innerWidth >= desktopBreakpoint } catch (_: Exception) { false }

        onDispose {
            window.removeEventListener("resize", resizeHandler)
        }
    }

    // Theme-aware colors
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT
    val headerBgColor = if (isLight) "rgba(255, 255, 255, 0.95)" else "rgba(6, 8, 11, 0.95)"
    val borderColor = if (isLight) "rgba(60, 131, 239, 0.2)" else "rgba(60, 131, 239, 0.15)"
    val textColor = if (isLight) Colors.Black else Colors.White

    // Get current path to highlight active nav item
    val currentPath = ctx.route.path

    // Main Header
    Div({
        style {
            width(100.percent)
            property("box-sizing", "border-box")
            position(Position.Fixed)
            top(0.px)
            left(0.px)
            right(0.px)
            property("z-index", "1000")
            // Responsive padding using media queries
            property("padding", "8px 12px") // Mobile default
            property("backdrop-filter", "blur(20px)")
            property("-webkit-backdrop-filter", "blur(20px)")
            property("background", headerBgColor)
            property("border-bottom", "1px solid $borderColor")
            property("box-shadow", if (isLight) "0 2px 20px rgba(0, 0, 0, 0.08)" else "0 4px 30px rgba(0, 0, 0, 0.3)")
            property("overflow", "visible")
        }
    }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
                        property("border-radius", "10px")
                        property("padding", "6px 10px")
                        property("margin-right", "8px")
                        property("cursor", "pointer")
                        property("flex-shrink", "0")
                    }
                    onClick { ctx.router.navigateTo("/") }
                }) {
                    SpanText(
                        "AK",
                        modifier = Modifier
                            .fontSize(16.px)
                            .fontWeight(FontWeight.Bold)
                            .color(Colors.White)
                    )
                }

                // Brand name (hidden on mobile using CssStyle)
                Box(modifier = BrandNameStyle.toModifier().onClick { ctx.router.navigateTo("/") }) {
                    SpanText(
                        "Ashwani Kumar",
                        modifier = BrandTextStyle.toModifier()
                            .fontWeight(FontWeight.Bold)
                            .color(textColor)
                    )
                }
            }

            // Navigation Links - visible only on desktop (>= 992px)
            if (isDesktop) {
                Div({
                    style {
                        display(DisplayStyle.Flex)
                        alignItems(AlignItems.Center)
                        gap(4.px)
                    }
                }) {
                navItems.forEach { item ->
                    val isActive = currentPath == item.path ||
                        (item.path == "/" && currentPath.isEmpty())
                    val isHovered = hoveredItem == item.id


                    Div({
                        style {
                            property("padding", "8px 16px")
                            property("border-radius", "20px")
                            property("cursor", "pointer")
                            property("transition", "all 0.3s ease")
                            property("position", "relative")
                            property("white-space", "nowrap")

                            if (isActive) {
                                property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.25), rgba(127, 82, 255, 0.25))")
                                property("border", "1px solid rgba(60, 131, 239, 0.4)")
                            } else if (isHovered) {
                                property("background", "rgba(60, 131, 239, 0.1)")
                            }
                        }
                        onMouseEnter { hoveredItem = item.id }
                        onMouseLeave { hoveredItem = null }
                        onClick { ctx.router.navigateTo(item.path) }
                    }) {
                        SpanText(
                            item.label,
                            modifier = Modifier
                                .fontSize(14.px)
                                .fontWeight(if (isActive) FontWeight.SemiBold else FontWeight.Normal)
                                .color(if (isActive) sitePal.brand.primary else textColor)
                        )
                    }
                }
                }
            }

            // Right side: Theme Toggle + Download Button + Mobile Menu
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                // Theme Toggle
                Box(modifier = Modifier.margin(right = 8.px)) {
                    ThemeToggle(compact = true)
                }

                // Download Resume Button
                Div({
                    style {
                        property("background", "linear-gradient(135deg, #3C83EF, #7F52FF)")
                        property("border-radius", "20px")
                        property("padding", "6px 12px")
                        property("cursor", "pointer")
                        property("transition", "all 0.3s ease")
                        property("border", "none")
                        property("box-shadow", "0 4px 15px rgba(60, 131, 239, 0.3)")
                        property("flex-shrink", "0")
                        display(DisplayStyle.Flex)
                        alignItems(AlignItems.Center)
                        gap(4.px)
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
                        modifier = Modifier.fontSize(12.px)
                    )

                    // Button text (hidden on small screens using CssStyle)
                    Box(modifier = ResumeTextStyle.toModifier()) {
                        SpanText(
                            "Resume",
                            modifier = Modifier
                                .fontSize(13.px)
                                .fontWeight(FontWeight.SemiBold)
                                .color(Colors.White)
                        )
                    }
                }

                // Mobile Hamburger Menu Button - visible on mobile/tablet, hidden on desktop
                // Using isDesktop state for immediate responsive control on first load
                if (!isDesktop) {
                    Div({
                        style {
                            property("background", if (mobileMenuOpen) "linear-gradient(135deg, #3C83EF, #7F52FF)" else if (isLight) "rgba(60, 131, 239, 0.15)" else "rgba(255, 255, 255, 0.15)")
                            property("border", "1px solid ${if (mobileMenuOpen) "transparent" else if (isLight) "rgba(60, 131, 239, 0.3)" else "rgba(255, 255, 255, 0.25)"}")
                            property("transition", "all 0.3s ease")
                            property("border-radius", "8px")
                            property("padding", "8px 10px")
                            property("min-width", "40px")
                            property("min-height", "40px")
                            property("margin-left", "8px")
                            display(DisplayStyle.Flex)
                            alignItems(AlignItems.Center)
                            justifyContent(JustifyContent.Center)
                            property("-webkit-tap-highlight-color", "transparent")
                            property("cursor", "pointer")
                        }
                        onClick { mobileMenuOpen = !mobileMenuOpen }
                    }) {
                        SpanText(
                            if (mobileMenuOpen) "✕" else "☰",
                            modifier = Modifier
                                .fontSize(20.px)
                                .fontWeight(FontWeight.Bold)
                                .color(if (mobileMenuOpen) Colors.White else textColor)
                        )
                    }
                }
            }
        }
    }

    // Mobile Menu Dropdown - Only visible on mobile/tablet (controlled by mobileMenuOpen state)
    if (mobileMenuOpen && !isDesktop) {
        // Backdrop overlay to close menu when clicking outside
        Div({
            style {
                position(Position.Fixed)
                top(0.px)
                left(0.px)
                right(0.px)
                bottom(0.px)
                property("z-index", "997")
                property("background", "rgba(0, 0, 0, 0.3)")
            }
            onClick { mobileMenuOpen = false }
        }) {}

        // Mobile menu content
        Div({
            style {
                position(Position.Fixed)
                top(56.px)
                left(0.px)
                right(0.px)
                width(100.percent)
                property("z-index", "999")
                property("padding", "12px 16px")
                display(DisplayStyle.Flex)
                flexDirection(FlexDirection.Column)
                gap(4.px)
            }
        }) {
            Div({
                style {
                    property("background", if (isLight) "rgba(255, 255, 255, 0.98)" else "rgba(10, 13, 18, 0.98)")
                    property("backdrop-filter", "blur(20px)")
                    property("-webkit-backdrop-filter", "blur(20px)")
                    property("border-bottom", "1px solid $borderColor")
                    property("box-shadow", "0 8px 30px rgba(0, 0, 0, 0.2)")
                    property("padding", "12px 16px")
                    property("max-height", "calc(100vh - 56px)")
                    property("overflow-y", "auto")
                    property("border-radius", "12px")
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    gap(4.px)
                }
            }) {
            navItems.forEach { item ->
                val isActive = currentPath == item.path ||
                    (item.path == "/" && currentPath.isEmpty())

                Div({
                    style {
                        property("padding", "14px 16px")
                        property("border-radius", "10px")
                        property("cursor", "pointer")
                        property("transition", "all 0.2s ease")

                        if (isActive) {
                            property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.2), rgba(127, 82, 255, 0.2))")
                            property("border-left", "3px solid #3C83EF")
                        } else {
                            property("background", "transparent")
                        }
                    }
                    onClick {
                        ctx.router.navigateTo(item.path)
                        mobileMenuOpen = false
                    }
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
