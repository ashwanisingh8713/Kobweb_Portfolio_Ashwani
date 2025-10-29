package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.components.widgets.NavTabDataList
import com.mano.ashwa.navigation.Screen
import com.stevdza.san.kotlinbs.components.BSNavBar
import com.stevdza.san.kotlinbs.models.BackgroundStyle
import com.stevdza.san.kotlinbs.models.navbar.NavBarBrand
import com.stevdza.san.kotlinbs.models.navbar.NavBarButton
import com.stevdza.san.kotlinbs.models.navbar.NavBarExpand
import com.stevdza.san.kotlinbs.models.navbar.NavBarInputField
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.core.PageContext
import com.mano.ashwa.ThemeToggle
import com.mano.ashwa.toSitePalette
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.opacity
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun BSHeader(ctx: PageContext) {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()

    var selectedTabId by remember { mutableStateOf(Screen.Home.id) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .position(Position.Fixed)
            .top(0.percent)
//            .opacity(0.6) // To make translucent to HeaderBar
            .zIndex(10)
    ) {

    BSNavBar(
        modifier = Modifier.fillMaxWidth().color(sitePal.nearBackground),
        stickyTop = true,
        itemsAlignment = Alignment.CenterHorizontally,
        brand = NavBarBrand(
            title = "Ashwani's Portfolio",
            //image = "https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg",
            href = ""
        ),
        expand = NavBarExpand.LG,
        backgroundStyle = BackgroundStyle.Dark,
        // It creates Navigation Tabs and handles selection redirections and states
        items = NavTabDataList(
            ctx,
            selectedTabId,
            onSelect = { tabId ->
                selectedTabId = tabId
            }
        ),
        inputField = NavBarInputField(
            placeholder = "Search",
            value = "",
            onValueChange = {}
        ),
        button = NavBarButton(
            text = "Search",
            onClick = {}
        )
    )

    // Add the ThemeToggle as a right-aligned control in the header area and nudge it up so it visually sits inline
    Row(
        modifier = Modifier.fillMaxWidth().padding(right = 12.px, top = (-36).px),
        horizontalArrangement = Arrangement.End
    ) {
        ThemeToggle(compact = true)
    }
}
}
