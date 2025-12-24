package com.mano.ashwa.components.widgets

import androidx.compose.runtime.Composable
import com.mano.ashwa.model.ExperienceData
import com.mano.ashwa.model.ProjectData
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.flexWrap
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.width
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.toSitePalette
import com.varabyte.kobweb.silk.theme.colors.ColorMode

@Composable
fun SkillCardView(title: String, skills: List<String>, icon: String? = null,
                  color: Color = Colors.WhiteSmoke) {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()

    Div({
        style {
            width(100.percent)
            property("background", "linear-gradient(145deg, ${sitePal.cardColor}, rgba(11, 18, 32, 0.95))")
            property("border-radius", "16px")
            property("padding", "20px")
            property("border", "1px solid rgba(60, 131, 239, 0.15)")
            property("transition", "all 0.3s ease")
            property("cursor", "default")
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateY(-4px)"
            it.currentTarget.asDynamic().style.boxShadow = "0 12px 40px rgba(60, 131, 239, 0.2)"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.4)"
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateY(0)"
            it.currentTarget.asDynamic().style.boxShadow = "none"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.15)"
        }
    }) {
        Column(Modifier.gap(12.px)) {
            Row(Modifier.gap(12.px)) {
                if (icon != null) {
                    // Icon with gradient background
                    Div({
                        style {
                            property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.3), rgba(170, 54, 124, 0.3))")
                            property("border-radius", "12px")
                            property("padding", "10px")
                            property("display", "flex")
                            property("align-items", "center")
                            property("justify-content", "center")
                        }
                    }) {
                        SpanText(icon, Modifier.fontSize(28.px))
                    }
                }
                SpanText(title, Modifier.fontWeight(FontWeight.Bold).fontSize(20.px).color(sitePal.cardTitleColor))
            }
            Column(Modifier.gap(6.px)) {
                skills.forEach {
                    SpanText("• $it", Modifier.fontSize(15.px).color(sitePal.cardDescriptionColor))
                }
            }
        }
    }
}


@Composable
fun ExperienceCardView(data: ExperienceData) {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()

    Div({
        style {
            width(100.percent)
            property("background", "linear-gradient(145deg, ${sitePal.cardColor}, rgba(11, 18, 32, 0.95))")
            property("border-radius", "16px")
            property("padding", "20px")
            property("border", "1px solid rgba(60, 131, 239, 0.15)")
            property("transition", "all 0.3s ease")
            property("cursor", "default")
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateY(-4px)"
            it.currentTarget.asDynamic().style.boxShadow = "0 12px 40px rgba(60, 131, 239, 0.2)"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.4)"
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateY(0)"
            it.currentTarget.asDynamic().style.boxShadow = "none"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.15)"
        }
    }) {
        Column(Modifier.gap(10.px)) {
            Row(Modifier.gap(12.px)) {
                if (data.icon.isNotEmpty()) {
                    // Icon with gradient background
                    Div({
                        style {
                            property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.3), rgba(170, 54, 124, 0.3))")
                            property("border-radius", "12px")
                            property("padding", "10px")
                            property("display", "flex")
                            property("align-items", "center")
                            property("justify-content", "center")
                        }
                    }) {
                        SpanText(data.icon, Modifier.fontSize(28.px))
                    }
                }
                Column {
                    Row {
                        SpanText(
                            data.companyName,
                            Modifier.fontWeight(FontWeight.Bold).fontSize(20.px).color(sitePal.cardTitleColor)
                        )
                        Box(modifier = Modifier.width(10.px))
                        SpanText(
                            "(${data.duration})",
                            Modifier.fontWeight(FontWeight.Bold).fontSize(14.px)
                                .padding(top = 5.px).color(sitePal.cardSubTitleColor)
                        )
                    }
                    SpanText(data.role, Modifier.fontWeight(FontWeight.Bold).fontSize(14.px).color(sitePal.brand.primary))
                }
            }
            Column(Modifier.gap(4.px)) {
                data.skills.forEach {
                    SpanText("• $it", Modifier.fontSize(15.px).color(sitePal.cardDescriptionColor))
                }
            }
        }
    }
}

@Composable
fun ChipLayout(items: List<String>, chipLayoutColor:Color, chipColor: Color, chipTextColor:Color) { // Changed default color
    Div({
        style {
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            // Add spacing between chips so the layout background is visible
            property("gap", "10px")
            property("margin-top", "12px")
            property("margin-bottom", "8px")
            width(100.percent)
            // Gradient background for layout with subtle glow
            property("background", "linear-gradient(135deg, ${chipLayoutColor}, rgba(60, 131, 239, 0.05))")
            property("border-radius", "14px")
            // Add padding so the layout background frames the chips
            property("padding", "14px 16px")
            // Subtle border for definition
            property("border", "1px solid rgba(125, 211, 252, 0.12)")
        }
    }) {
        items.forEach { tech ->
            Chip(tech, chipColor, chipTextColor)
        }
    }
}

@Composable
fun Chip(text: String, chipColor: Color, chipTextColor: Color) { // Updated to increase visibility on dark backgrounds
    Box(
        Modifier
            .backgroundColor(chipColor)
            .borderRadius(20.px)
            // Larger padding for better readability
            .padding(left = 16.px, right = 16.px, top = 8.px, bottom = 8.px)
    ) {
        org.jetbrains.compose.web.dom.Span({
            style {
                property("white-space", "nowrap")
                property("font-size", "13px")
                property("font-weight", "500")
                property("letter-spacing", "0.3px")
                property("display", "flex")
                property("align-items", "center")
                property("justify-content", "center")
                // Add subtle glow effect
                property("text-shadow", "0 0 8px rgba(125, 211, 252, 0.3)")
            }
        }) {
            SpanText(text, Modifier.fontSize(13.px).color(chipTextColor))
        }
    }
}

@Composable
fun ProjectCardView(data: ProjectData) {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()

    Div({
        style {
            width(100.percent)
            property("background", "linear-gradient(145deg, ${sitePal.cardColor}, rgba(11, 18, 32, 0.95))")
            property("border-radius", "16px")
            property("padding", "20px")
            property("border", "1px solid rgba(60, 131, 239, 0.15)")
            property("transition", "all 0.3s ease")
            property("cursor", "default")
        }
        onMouseEnter {
            it.currentTarget.asDynamic().style.transform = "translateY(-4px)"
            it.currentTarget.asDynamic().style.boxShadow = "0 12px 40px rgba(60, 131, 239, 0.2)"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.4)"
        }
        onMouseLeave {
            it.currentTarget.asDynamic().style.transform = "translateY(0)"
            it.currentTarget.asDynamic().style.boxShadow = "none"
            it.currentTarget.asDynamic().style.borderColor = "rgba(60, 131, 239, 0.15)"
        }
    }) {
        Column(Modifier.gap(12.px)) {
            Row(Modifier.gap(12.px)) {
                if (data.icon.isNotEmpty()) {
                    // Icon with gradient background
                    Div({
                        style {
                            property("background", "linear-gradient(135deg, rgba(60, 131, 239, 0.3), rgba(170, 54, 124, 0.3))")
                            property("border-radius", "12px")
                            property("padding", "10px")
                            property("display", "flex")
                            property("align-items", "center")
                            property("justify-content", "center")
                        }
                    }) {
                        SpanText(data.icon, Modifier.fontSize(28.px))
                    }
                }
                Column {
                    SpanText(
                        data.name,
                        Modifier.fontWeight(FontWeight.Bold).fontSize(20.px).color(sitePal.cardTitleColor)
                    )
                    SpanText(
                        "(${data.duration})",
                        Modifier.fontWeight(FontWeight.Bold).fontSize(14.px).padding(top = 5.px).color(sitePal.cardSubTitleColor)
                    )
                    SpanText(data.role, Modifier.fontWeight(FontWeight.Bold).fontSize(14.px).color(sitePal.brand.primary))
                }
            }
            SpanText(data.description, Modifier.fontSize(15.px).color(sitePal.cardDescriptionColor))
            ChipLayout(data.technologies, chipLayoutColor = sitePal.chipLayoutColor, chipColor = sitePal.chipColor, chipTextColor = sitePal.chipTextColor)
        }
    }
}
