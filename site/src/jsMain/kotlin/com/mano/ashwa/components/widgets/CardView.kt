package com.mano.ashwa.components.widgets

import androidx.compose.runtime.Composable
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
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px

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
fun ExperienceCardView(title: String, subtitle: String, skills: List<String>, icon: String? = null,
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
                Column {
                    SpanText(title, Modifier.fontWeight(FontWeight.Bold).fontSize(20.px))
                    SpanText(subtitle, Modifier.fontWeight(FontWeight.Bold).fontSize(14.px))
                }
            }
            Column {
                skills.forEach {
                    SpanText("• $it", Modifier.fontSize(16.px).color(Colors.DarkSlateGray))
                }
            }
        }
    }
}