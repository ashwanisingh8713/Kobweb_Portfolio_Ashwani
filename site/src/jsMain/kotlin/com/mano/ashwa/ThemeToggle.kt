package com.mano.ashwa

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement

@Composable
fun ThemeToggle(compact: Boolean = false) {
    val appColorState = LocalAppColorMode.current
    val current = appColorState.value
    val isLight = current == ColorMode.LIGHT

    fun toggleTheme() {
        val newMode = if (isLight) ColorMode.DARK else ColorMode.LIGHT
        val modeName = if (isLight) "dark" else "light"

        appColorState.value = newMode

        // Update HTML attribute for any CSS that relies on it
        try {
            (document.documentElement as? HTMLElement)?.setAttribute("data-color-mode", modeName)
        } catch (_: Throwable) { }

        // Also set a few CSS variables and inline styles so visual changes show immediately
        try {
            val sitePal = if (newMode == ColorMode.LIGHT) SitePalettes.light else SitePalettes.dark
            val bg = sitePal.nearBackground.toString()
            val text = if (newMode == ColorMode.DARK) Colors.White.toString() else Colors.Black.toString()
            val brandPrimary = sitePal.brand.primary.toString()
            val brandAccent = sitePal.brand.accent.toString()

            (document.documentElement as? HTMLElement)?.style?.setProperty("--site-near-background", bg)
            (document.documentElement as? HTMLElement)?.style?.setProperty("--site-color", text)
            (document.documentElement as? HTMLElement)?.style?.setProperty("--site-brand-primary", brandPrimary)
            (document.documentElement as? HTMLElement)?.style?.setProperty("--site-brand-accent", brandAccent)

            // Inline body background/color as a fallback for styles generated at init-time
            document.body?.style?.backgroundColor = bg
            document.body?.style?.color = text
        } catch (_: Throwable) { }
    }

    val size = if (compact) 36.px else 44.px
    val iconSize = if (compact) 18.px else 22.px

    // Single toggle button with sun/moon icon
    Div({
        style {
            width(size)
            height(size)
            property("border-radius", "50%")
            property("cursor", "pointer")
            property("transition", "all 0.3s ease")
            display(DisplayStyle.Flex)
            alignItems(AlignItems.Center)
            justifyContent(JustifyContent.Center)

            if (isLight) {
                // Light mode - show moon icon, dark background
                property("background", "linear-gradient(135deg, #1e293b, #334155)")
                property("box-shadow", "0 2px 8px rgba(0, 0, 0, 0.2)")
            } else {
                // Dark mode - show sun icon, light/golden background
                property("background", "linear-gradient(135deg, #fbbf24, #f59e0b)")
                property("box-shadow", "0 2px 8px rgba(251, 191, 36, 0.4)")
            }
        }
        onClick { toggleTheme() }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "scale(1.1)"
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "scale(1)"
        }
    }) {
        // Icon: Sun for dark mode (click to go light), Moon for light mode (click to go dark)
        SpanText(
            text = if (isLight) "🌙" else "☀️",
            modifier = Modifier.fontSize(iconSize)
        )
    }
}
