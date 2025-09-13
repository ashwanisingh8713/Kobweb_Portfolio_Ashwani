package com.mano.ashwa.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "/")
    object About : Screen(route = "/about")
    object Skill : Screen(route = "/skill")
    object Project : Screen(route = "/project")
    object Experiences : Screen(route = "/experiences")
    object Portfolio : Screen(route = "/portfolio")
    object Articles : Screen(route = "/articles")
    object ContactUs : Screen(route = "/contact-us")
}