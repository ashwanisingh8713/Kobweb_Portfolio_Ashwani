package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.mano.ashwa.components.layouts.PageLayoutData
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout

@InitRoute
fun initAboutMePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("About Me | Ashwani Kumar Singh"))
}

@Page("/aboutme")
@Layout(".components.layouts.PageLayout")
@Composable
fun AboutMe() {
    // You can add content here for the About Me page
}
