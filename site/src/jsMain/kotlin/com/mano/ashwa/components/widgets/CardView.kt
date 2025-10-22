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

@Composable
fun SkillCardView(title: String, skills: List<String>, icon: String? = null,
                  color: Color = Colors.WhiteSmoke) {
    Box(
        Modifier
            .fillMaxWidth()
            .backgroundColor(color)
            .borderRadius(12.px)
            .padding(16.px)
    ) {
        Column(Modifier.gap(8.px)) {
            Row(Modifier.gap(8.px)) {
                if (icon != null) {
                    SpanText(icon, Modifier.fontSize(24.px))
                }
                SpanText(title, Modifier.fontWeight(FontWeight.Bold).fontSize(20.px))
            }
            Column {
                skills.forEach {
                    SpanText("• $it", Modifier.fontSize(16.px).color(Colors.DarkSlateGray))
                }
            }
        }
    }
}


@Composable
fun ExperienceCardView(data: ExperienceData) {
    Box(
        Modifier
            .fillMaxWidth()
            .backgroundColor(data.color)
            .borderRadius(12.px)
            .padding(16.px)
    ) {
        Column(Modifier.gap(8.px)) {
            Row(Modifier.gap(8.px)) {
                if (data.icon.isNotEmpty()) {
                    SpanText(data.icon, Modifier.fontSize(24.px))
                }
                Column {
                    Row {
                        SpanText(
                            data.companyName,
                            Modifier.fontWeight(FontWeight.Bold).fontSize(20.px)
                        )
                        Box(modifier = Modifier.width(10.px))
                        SpanText(
                            "(${data.duration})",
                            Modifier.fontWeight(FontWeight.Bold).fontSize(14.px)
                                .padding(top = 5.px)
                        )
                    }
                    SpanText(data.role, Modifier.fontWeight(FontWeight.Bold).fontSize(14.px))
                }
            }
            Column {
                data.skills.forEach {
                    SpanText("• $it", Modifier.fontSize(16.px).color(Colors.DarkSlateGray))
                }
            }
        }
    }
}

@Composable
fun ChipLayout(items: List<String>, chipColor: Color = Colors.Gray) {
    Div({
        style {
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            property("gap", "0px")
            property("margin-top", "4px")
            property("margin-bottom", "4px")
            property("margin-right", "4px")
            width(100.percent)
        }
    }) {
        items.forEach { tech ->
            Chip(tech, chipColor)
        }
    }
}

@Composable
fun Chip(text: String, color: Color = Colors.Gray) {
    Box(
        Modifier
            .backgroundColor(color)
            .borderRadius(16.px)
            .padding(left = 12.px, right = 12.px, top = 4.px, bottom = 4.px)
            .margin(2.px)
    ) {
        org.jetbrains.compose.web.dom.Span({
            style {
                property("white-space", "nowrap")
                property("font-size", "14px")
                property("color", Colors.DarkSlateBlue.toString()) // More contrast
                property("margin-right", "8px")
                property("margin-bottom", "8px")
                property("box-shadow", "0 2px 8px 0 rgba(0, 120, 255, 0.10)") // Subtle shadow
                property("border", "1px solid #90caf9") // Light blue border
            }
        }) {
            org.jetbrains.compose.web.dom.Text(text)
        }
    }
}

@Composable
fun ProjectCardView(data: ProjectData) {
    Box(
        Modifier
            .fillMaxWidth()
            .backgroundColor(data.color)
            .borderRadius(12.px)
            .padding(16.px)
    ) {
        Column(Modifier.gap(8.px)) {
            Row(Modifier.gap(8.px)) {
                if (data.icon.isNotEmpty()) {
                    SpanText(data.icon, Modifier.fontSize(24.px))
                }
                Column {
                    SpanText(
                        data.name,
                        Modifier.fontWeight(FontWeight.Bold).fontSize(20.px)
                    )
                    SpanText(
                        "(${data.duration})",
                        Modifier.fontWeight(FontWeight.Bold).fontSize(14.px).padding(top = 5.px)
                    )
                    SpanText(data.role, Modifier.fontWeight(FontWeight.Bold).fontSize(14.px))
                }
            }
            SpanText(data.description, Modifier.fontSize(16.px).color(Colors.DarkSlateGray))
            ChipLayout(data.technologies)
        }
    }
}
