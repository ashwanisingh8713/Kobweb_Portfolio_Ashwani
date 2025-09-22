package com.mano.ashwa.pages

import org.jetbrains.compose.web.css.fr
import androidx.compose.runtime.Composable
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.widgets.SkillCardView
import com.mano.ashwa.model.SkillData
import com.mano.ashwa.navigation.Skill_Route
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.compose.ui.modifiers.*
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.DisplayStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.Div

@InitRoute
fun initSkillPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Ashwani Skills"))
}

@Page(Skill_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun SkillPage() {
    Box(Modifier.fillMaxWidth().backgroundColor(Colors.WhiteSmoke).padding(32.px)) {
        Column(
            Modifier.gap(32.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText("My Skills", Modifier.fontSize(32.px).fontWeight(FontWeight.Bold).color(Colors.DarkSlateBlue))

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
                skillCards.forEach { card ->
                    SkillCardView(
                        title = card.title,
                        skills = card.skills,
                        icon = card.icon,
                        color = card.color
                    )
                }
            }
        }
    }
}


private val skillCards = listOf(
    SkillData(
        title = "Multiplatform & Android",
        skills = listOf(
            "Kotlin Multiplatform Development (Android, iOS, Web)",
            "Android Native App Development",
            "JetPack Libs & ComposeUI"
        ),
        icon = "📱",
        color = Colors.LightBlue
    ),
    SkillData(
        title = "Architecture & Patterns",
        skills = listOf(
            "SOLID principles",
            "MVVM-MVI display pattern",
            "Expect/Actual, Coroutine, Flow"
        ),
        icon = "🏗️",
        color = Colors.LightGoldenRodYellow
    ),
    SkillData(
        title = "Libraries & Tools",
        skills = listOf(
            "DI (KOIN, HILT)",
            "Ktor",
            "GraphQL integration & Webpack configuration",
            "Firebase Full Integration"
        ),
        icon = "🛠️",
        color = Colors.LightGreen
    ),
    SkillData(
        title = "Debugging & Performance",
        skills = listOf(
            "Log back stack tracking",
            "Debugging, Memory Profiler",
            "Performance bottlenecks, Adb-tools"
        ),
        icon = "🔍",
        color = Colors.MistyRose
    ),
    SkillData(
        title = "Backend & Integration",
        skills = listOf(
            "GoLang Microservices (GIN)",
            "Google DFP & IMA Ads",
            "Client handling & support"
        ),
        icon = "🔗",
        color = Colors.Lavender
    ),
    SkillData(
        title = "Project & Team",
        skills = listOf(
            "Project Management Tools",
            "Team Managing, Problem solving",
            "Wireframe creation (Diagrams, Miro)"
        ),
        icon = "👥",
        color = Colors.HoneyDew
    ),
    SkillData(
        title = "Testing & Reactive",
        skills = listOf(
            "UnitTest cases writing",
            "Reactive Programming"
        ),
        icon = "✅",
        color = Colors.AliceBlue
    ),
    SkillData(
        title = "AI & Wireframing",
        skills = listOf(
            "AI Tools & Prompt Engineering"
        ),
        icon = "🤖",
        color = Colors.PaleTurquoise
    )
)


