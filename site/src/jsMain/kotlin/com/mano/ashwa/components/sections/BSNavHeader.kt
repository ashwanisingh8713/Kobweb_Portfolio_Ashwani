package com.mano.ashwa.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mano.ashwa.components.widgets.NavTabDataList
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



@Composable
fun BSHeader(ctx: PageContext) {
    var selectedTabId by remember { mutableStateOf(com.mano.ashwa.navigation.Screen.Home.id) }
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
}
