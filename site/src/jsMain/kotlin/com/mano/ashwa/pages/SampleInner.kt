package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.sections.ResumeSection
import com.mano.ashwa.toSitePalette



@InitRoute
fun initHomePage2(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Ashwani Kumar"))
}

@Page("lpop")
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage2() {
    Row(HeroContainerStyle.toModifier()) {
        Box {
            val sitePalette = ColorMode.current.toSitePalette()

            Column(Modifier.gap(2.cssRem)) {
                // Write this resume in Kobweb Compose App.
                //  /Users/ashwani/Kobweb/Kobweb_Portfolio_Ashwani/site/Resume/Ashwani Android Tech Lead & KMP Developer.pdf

                ResumeSection()
                //SkillPage()
            }
        }
    }
}
