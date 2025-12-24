package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.StyleVariable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.vh
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.sections.Banner
import com.mano.ashwa.sections.HomeAboutSection
import com.mano.ashwa.sections.HomeSkillsSection
import com.mano.ashwa.sections.HomeFeaturedProjectsSection
import com.mano.ashwa.sections.HomeExperienceSection

// Container that has a tagline and grid on desktop, and just the tagline on mobile
val HeroContainerStyle = CssStyle {
    base { Modifier.fillMaxWidth().gap(2.cssRem) }
    Breakpoint.MD { Modifier.margin { top(20.vh) } }
}

// A demo grid that appears on the homepage because it looks good
val HomeGridStyle = CssStyle.base {
    Modifier
        .gap(0.5.cssRem)
        .width(70.cssRem)
        .height(18.cssRem)
}

private val GridCellColorVar by StyleVariable<Color>()
val HomeGridCellStyle = CssStyle.base {
    Modifier
        .backgroundColor(GridCellColorVar.value())
        .boxShadow(blurRadius = 0.6.cssRem, color = GridCellColorVar.value())
        .borderRadius(1.cssRem)
}




@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Ashwani Kumar Singh | Android Tech Lead & KMP Developer"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    // When @Layout is used, then mentioned "PageLayout" UI comes automatically in calling page.
    // the "content" parameter which is here as lambda, gets remaining UI of the page
    // Below full code is referred from PageLayout.kt -> PageLayout function parameter 'content'
    // here whatever is written those will go inside "content" parameter, because it has last parameter as Lambda function

    // Hero Banner with animated text and image
    Banner()

    // About Me section with stats and core technologies
    HomeAboutSection()

    // Skills section showcasing expertise areas
    HomeSkillsSection()

    // Featured projects section
    HomeFeaturedProjectsSection()

    // Experience timeline section
    HomeExperienceSection()

    // Footer is handled by PageLayout
}
