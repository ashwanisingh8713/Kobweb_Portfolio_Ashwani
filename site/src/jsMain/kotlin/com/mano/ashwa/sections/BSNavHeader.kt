package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import com.mano.ashwa.components.widgets.NavTabDataList
import com.mano.ashwa.navigation.Screen
import com.stevdza.san.kotlinbs.components.BSNavBar
import com.stevdza.san.kotlinbs.models.BackgroundStyle
import com.stevdza.san.kotlinbs.models.navbar.NavBarBrand
import com.stevdza.san.kotlinbs.models.navbar.NavBarExpand
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import org.jetbrains.compose.web.css.px
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div

@Composable
fun BSHeader(ctx: PageContext) {
    // Render navbar
    Div({
        // Keep the container full width and position relative so the download link can be placed to the right
        style {
            // minimal style: ensure children are arranged
            property("position", "relative")
            property("width", "100%")
        }
    }) {
        BSNavBar(
            modifier = Modifier.fillMaxWidth(),
            stickyTop = true,
            itemsAlignment = Alignment.CenterHorizontally,
            brand = NavBarBrand(
                title = "KotlinBootstrap",
                image = "https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg",
                href = "#"
            ),
            expand = NavBarExpand.LG,
            backgroundStyle = BackgroundStyle.Dark,
            // It creates Navigation Tabs and handles selection redirections and states
            items = NavTabDataList(
                ctx,
                Screen.Home.id,
                onSelect = { _ -> /* no-op selection handler; navigation still occurs inside NavTab */ }
            )
        )

        // Render an anchor styled as a Bootstrap button and positioned to the right of the navbar.
        // We place it after BSNavBar but still inside the relative container so absolute positioning works.
        A(href = "/Resume/ABHISHEK_VERMA_Resume.pdf", attrs = {
            attr("download", "ABHISHEK_VERMA_Resume.pdf")
            attr("aria-label", "Download ABHISHEK VERMA resume")
            // Use Bootstrap button classes so the button is visible on the dark navbar
            attr("class", "btn btn-outline-light")
            // Position the anchor to the right/top of the navbar
            attr("style", "position:absolute; right:12px; top:8px; z-index:1030;")
        }) {
            SpanText(
                "Download Resume",
                modifier = Modifier.padding(6.px).styleModifier {
                    // keep small padding but rely on bootstrap for look
                    property("cursor", "pointer")
                }
            )
        }
    }
}
