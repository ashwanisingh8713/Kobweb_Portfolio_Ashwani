package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.mano.ashwa.navigation.Project_Route
import com.mano.ashwa.navigation.Skill_Route
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText


@Page(Project_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun MyProjects() {
    // You can add content here for the My Skills page
    SpanText("My Projects Page")
}