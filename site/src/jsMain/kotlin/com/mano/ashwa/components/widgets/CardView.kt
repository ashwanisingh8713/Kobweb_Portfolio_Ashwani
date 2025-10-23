package com.mano.ashwa.components.widgets

import androidx.compose.runtime.Composable
import com.mano.ashwa.model.ExperienceData
import com.mano.ashwa.model.ProjectData
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.flexWrap
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width

// Helper to convert a Color to CSS hex string. Color.toString() produces a CSS-friendly value for Kobweb colors,
// but guard it to default to white if empty.
private fun Color.toCssString(): String {
    val s = this.toString()
    return if (s.isNotBlank()) s else "#ffffff"
}

@Composable
fun SkillCardView(title: String, skills: List<String>, icon: String? = null, color: Color = Colors.Transparent) {
    // Determine an accent color. If caller passed Transparent, use a subtle visible fallback.
    val accent = if (color == Colors.Transparent) "rgba(255,255,255,0.06)" else color.toCssString()

    // Use a dark translucent background for the card, keep a subtle colored left border as accent
    Box(
        Modifier
            .fillMaxWidth()
            .backgroundColor(Colors.Transparent)
            .styleModifier { property("background-color", "rgba(255,255,255,0.03)") }
            // Draw a left stripe using an inset box-shadow so the accent respects the card's border-radius.
            .styleModifier { property("box-shadow", "inset 6px 0 0 0 $accent") }
            // Keep a border-left as a fallback for environments where box-shadow behaves differently.
            .styleModifier { property("border-left", "6px solid $accent") }
            .borderRadius(12.px)
            .padding(16.px)
    ) {
        Column(Modifier.gap(8.px)) {
            Row(Modifier.gap(8.px)) {
                if (icon != null) {
                    SpanText(icon, Modifier.fontSize(24.px).styleModifier { property("color", "#ffffff") })
                }
                SpanText(title, Modifier.fontWeight(FontWeight.Bold).fontSize(20.px).styleModifier { property("color", "#ffffff") })
            }
            Column {
                skills.forEach {
                    SpanText("\u2022 $it", Modifier.fontSize(16.px).styleModifier { property("color", "#cfcfcf") })
                }
            }
        }
    }
}


@Composable
fun ExperienceCardView(data: ExperienceData) {
    // Determine an accent color. If caller passed Transparent, use a subtle visible fallback.
    val accent = if (data.color == Colors.Transparent) "rgba(255,255,255,0.06)" else data.color.toCssString()

    Box(
        Modifier
            .fillMaxWidth()
            // Dark translucent background and subtle left accent
            .backgroundColor(Colors.Transparent)
            .styleModifier { property("background-color", "rgba(255,255,255,0.03)") }
            // Use inset box-shadow so the accent stripe respects border-radius
            .styleModifier { property("box-shadow", "inset 6px 0 0 0 $accent") }
            .styleModifier { property("border-left", "6px solid $accent") }
            .borderRadius(12.px)
            .padding(16.px)
    ) {
        Column(Modifier.gap(8.px)) {
            Row(Modifier.gap(8.px)) {
                if (data.icon.isNotEmpty()) {
                    SpanText(data.icon, Modifier.fontSize(24.px).styleModifier { property("color", "#ffffff") })
                }
                Column {
                    Row {
                        SpanText(
                            data.companyName,
                            Modifier.fontWeight(FontWeight.Bold).fontSize(20.px).styleModifier { property("color", "#ffffff") }
                        )
                        Box(modifier = Modifier.width(10.px))
                        SpanText(
                            "(${data.duration})",
                            Modifier.fontWeight(FontWeight.Bold).fontSize(14.px)
                                .padding(top = 5.px).styleModifier { property("color", "#cfcfcf") }
                        )
                    }
                    SpanText(data.role, Modifier.fontWeight(FontWeight.Bold).fontSize(14.px).styleModifier { property("color", "#ffffff") })
                }
            }
            Column {
                data.skills.forEach {
                    SpanText("\u2022 $it", Modifier.fontSize(16.px).styleModifier { property("color", "#cfcfcf") })
                }
            }
        }
    }
}

@Composable
fun ChipLayout(items: List<String>, chipColor: String = "#2196f3") { // color as CSS string
    Div({
        style {
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            property("gap", "0px")
            property("margin-top", "4px")
            property("margin-bottom", "4px")
            property("margin-right", "4px")
            width(100.percent)
            // Dark background for the chip layout
            property("background-color", "#1b1b1b")
            property("border-radius", "12px")
            property("padding", "8px 0")
        }
    }) {
        items.forEach { tech ->
            Chip(tech, chipColor)
        }
    }
}

@Composable
fun Chip(text: String, color: String = "#2196f3") { // color as CSS string
    Box(
        Modifier
            .backgroundColor(Colors.Transparent)
            .styleModifier { property("background-color", color) }
            .borderRadius(16.px)
            .padding(left = 12.px, right = 12.px, top = 4.px, bottom = 4.px)
            .margin(left = 5.px, top = 3.px, right = 3.px, bottom = 3.px)
    ) {
        org.jetbrains.compose.web.dom.Span({
            style {
                property("white-space", "nowrap")
                property("font-size", "14px")
                property("color", "#ffffff")
                property("margin-right", "8px")
                property("margin-bottom", "8px")
                property("box-shadow", "0 2px 8px 0 rgba(0, 120, 255, 0.10)")
                property("display", "flex")
                property("align-items", "center")
                property("justify-content", "center") // Center align text
                property("height", "100%")
            }
        }) {
            org.jetbrains.compose.web.dom.Text(text)
        }
    }
}

@Composable
fun ProjectCardView(data: ProjectData) {
    // Determine an accent color. If caller passed Transparent, use a subtle visible fallback.
    val accent = if (data.color == Colors.Transparent) "rgba(255,255,255,0.06)" else data.color.toCssString()

    Box(
        Modifier
            .fillMaxWidth()
            .backgroundColor(Colors.Transparent)
            .styleModifier { property("background-color", "rgba(255,255,255,0.03)") }
            // Use inset box-shadow so the accent stripe respects border-radius
            .styleModifier { property("box-shadow", "inset 6px 0 0 0 $accent") }
            .styleModifier { property("border-left", "6px solid $accent") }
            .borderRadius(12.px)
            .padding(16.px)
    ) {
        Column(Modifier.gap(8.px)) {
            Row(Modifier.gap(8.px)) {
                if (data.icon.isNotEmpty()) {
                    SpanText(data.icon, Modifier.fontSize(24.px).styleModifier { property("color", "#ffffff") })
                }
                Column {
                    SpanText(
                        data.name,
                        Modifier.fontWeight(FontWeight.Bold).fontSize(20.px).styleModifier { property("color", "#ffffff") }
                    )
                    SpanText(
                        "(${data.duration})",
                        Modifier.fontWeight(FontWeight.Bold).fontSize(14.px).padding(top = 5.px).styleModifier { property("color", "#cfcfcf") }
                    )
                    SpanText(data.role, Modifier.fontWeight(FontWeight.Bold).fontSize(14.px).styleModifier { property("color", "#ffffff") })
                }
            }
            SpanText(data.description, Modifier.fontSize(16.px).styleModifier { property("color", "#cfcfcf") })
            ChipLayout(data.technologies)
        }
    }
}
