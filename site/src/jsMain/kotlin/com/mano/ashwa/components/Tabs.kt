package com.mano.ashwa.components

import androidx.compose.runtime.Composable
import androidx.compose.web.events.SyntheticMouseEvent
import com.mano.ashwa.navigation.Screen
import com.varabyte.kobweb.navigation.Anchor
import kotlinx.browser.window
import org.jetbrains.compose.web.dom.Text

data class Tab(val name: String, val link: String)

val tabs = listOf(
	Tab("Home", Screen.Home.route),
	Tab("About Me", Screen.About.route),
	Tab("My Skills", Screen.Skill.route),
	Tab("My Projects", Screen.Project.route),
	Tab("Experiences", Screen.Experiences.route),
	Tab("Portfolio", Screen.Portfolio.route),
	Tab("Articles", Screen.Articles.route),
)

@Composable
fun Tab(tab: Tab, onClick: (SyntheticMouseEvent) -> Unit = {}) {
	Anchor(tab.link, {
		if (tab.link == window.location.pathname) classes("active")

		onClick(onClick)
	}) {
		Text(tab.name)
	}
}
