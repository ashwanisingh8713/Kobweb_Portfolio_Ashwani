package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.widgets.ExperienceCardView
import com.mano.ashwa.components.widgets.SkillCardView
import com.mano.ashwa.model.ExperienceData
import com.mano.ashwa.navigation.Experience_Route
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridTemplateColumns
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

@InitRoute
fun initExperiencesPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Ashwani Skills"))
}

@Page(Experience_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun Experiences() {
    // You can add content here for the My Experience page
    Box(Modifier.fillMaxWidth().backgroundColor(Colors.WhiteSmoke).padding(32.px)) {
        Column(
            Modifier.gap(32.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText("My Experiences", Modifier.fontSize(32.px).fontWeight(FontWeight.Bold).color(Colors.DarkSlateBlue))

            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .display(DisplayStyle.Grid)
                    .gridTemplateColumns {
                        repeat(4) { minmax(250.px, 1.fr) }
                    }
                    .gap(32.px)
                    .toAttrs()
            ) {
                experienceCards.forEach { card ->
                    ExperienceCardView(
                        title = card.title,
                        subtitle = card.subTitle,
                        skills = card.skills,
                        icon = card.icon,
                        color = card.color
                    )
                }
            }
        }
    }
}

private val experienceCards = listOf(
    ExperienceData(
        title = "Tech Mahindra",
        subTitle = "Tech Lead, May-2024 to Present",
        skills = listOf(
            "Log back stack tracking, Debugging,",
            "Android Native, JNI code implementation.",
            "Performance and Memory handling.",
            "NR & LTE network communications and monitoring.",
            "Agile Methodologies and Team Leadership.",
            "Code architecture, Mentoring and Code Reviews.",
            "Task Planning and Execution and Client Communication.",
            "Technologies: Kotlin, Java, C/C++, Android, JNI, Git, JIRA, Confluence",
            "Environment: Linux, Windows",
            "Domain: Telecom (5G, LTE, NR)",
            "Google Drive Upload & Avoid Duplicate Folder Creation",
            "File Handling, Multithreading, Retrofit, REST API",
            "Understanding and accumulating requirements, Making Flow Diagram, Providing Task breakup and estimation, distributing tasks"
        ),
        icon = "📱",
        color = Colors.LightBlue
    ),
)