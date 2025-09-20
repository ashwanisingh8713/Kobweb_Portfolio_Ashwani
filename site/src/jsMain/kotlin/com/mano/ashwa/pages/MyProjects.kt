package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.navigation.Project_Route
import com.mano.ashwa.navigation.Skill_Route
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText


@InitRoute
fun initMyProjectsPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Ashwani Projects"))
}

@Page(Project_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun MyProjects() {
    // You can add content here for the My Skills page
    SpanText("My Projects Page")
}