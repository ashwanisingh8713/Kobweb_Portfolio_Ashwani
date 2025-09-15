package com.mano.ashwa.pages

import org.jetbrains.compose.web.css.fr
import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.compose.ui.modifiers.*
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.DisplayStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.gridTemplateColumns
import org.jetbrains.compose.web.dom.Div

@Composable
fun SkillCard(title: String, skills: List<String>, icon: String? = null, color: Color = Colors.WhiteSmoke) {
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
private fun GridUI() {
    Div(
        attrs = Modifier
            .fillMaxWidth()
            .display(DisplayStyle.Grid)
            .gridTemplateColumns {
                repeat(autoFit) {
                    minmax(150.px, 1.fr)
                }
            }
            .gap(16.px)
            .padding(16.px)
            .toAttrs()
    ) {
        repeat(10) { index ->
            Box(
                modifier = Modifier
                    .size(100.px)
                    .backgroundColor(Colors.LightGray)
                    .borderRadius(8.px),
                contentAlignment = Alignment.Center
            ) {
                SpanText("Item $index")
            }
        }
    }
}

@Page("/skill")
@Composable
fun MySkills() {
    Box(Modifier.fillMaxWidth().backgroundColor(Colors.WhiteSmoke).padding(32.px)) {
        Column(
            Modifier.gap(32.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText("My Skills", Modifier.fontSize(32.px).fontWeight(FontWeight.Bold).color(Colors.DarkSlateBlue))

            GridUI()
            GridUI()

            Row(Modifier.gap(32.px), horizontalArrangement = Arrangement.Center) {
                SkillCard(
                    title = "Multiplatform & Android",
                    skills = listOf(
                        "Kotlin Multiplatform Development (Android, iOS, Web)",
                        "Android Native App Development",
                        "JetPack Libs & ComposeUI"
                    ),
                    icon = "📱",
                    color = Colors.LightBlue
                )
                SkillCard(
                    title = "Architecture & Patterns",
                    skills = listOf(
                        "SOLID principles",
                        "MVVM-MVI display pattern",
                        "Expect/Actual, Coroutine, Flow"
                    ),
                    icon = "🏗️",
                    color = Colors.LightGoldenRodYellow
                )
                SkillCard(
                    title = "Libraries & Tools",
                    skills = listOf(
                        "DI (KOIN, HILT)",
                        "Ktor",
                        "GraphQL integration & Webpack configuration",
                        "Firebase Full Integration"
                    ),
                    icon = "🛠️",
                    color = Colors.LightGreen
                )
                SkillCard(
                    title = "Debugging & Performance",
                    skills = listOf(
                        "Log back stack tracking",
                        "Debugging, Memory Profiler",
                        "Performance bottlenecks, Adb-tools"
                    ),
                    icon = "🔍",
                    color = Colors.MistyRose
                )
                SkillCard(
                    title = "Backend & Integration",
                    skills = listOf(
                        "GoLang Microservices (GIN)",
                        "Google DFP & IMA Ads",
                        "Client handling & support"
                    ),
                    icon = "🔗",
                    color = Colors.Lavender
                )
                SkillCard(
                    title = "Project & Team",
                    skills = listOf(
                        "Project Management Tools",
                        "Team Managing, Problem solving",
                        "Wireframe creation (Diagrams, Miro)"
                    ),
                    icon = "👥",
                    color = Colors.HoneyDew
                )
                SkillCard(
                    title = "Testing & Reactive",
                    skills = listOf(
                        "UnitTest cases writing",
                        "Reactive Programming"
                    ),
                    icon = "✅",
                    color = Colors.AliceBlue
                )
                SkillCard(
                    title = "AI & Wireframing",
                    skills = listOf(
                        "AI Tools & Prompt Engineering"
                    ),
                    icon = "🤖",
                    color = Colors.PaleTurquoise
                )
            }
        }
    }
}
