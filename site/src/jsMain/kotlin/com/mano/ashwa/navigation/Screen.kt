package com.mano.ashwa.navigation


const val Skill_Route = "/skill"
const val About_Route = "/about"
const val Project_Route = "/project"
const val Experience_Route = "/experiences"
const val CoverLetter_Route = "/cover-letter"
const val ContactMe_Route = "/contact-me"

sealed class Screen(val route: String) {
    object Home : Screen(route = "/")
    object About : Screen(route = About_Route)
    object Skill : Screen(route = Skill_Route)
    object Project : Screen(route = Project_Route)
    object Experiences : Screen(route = Experience_Route)
    object CoverLetter : Screen(route = CoverLetter_Route)
    object ContactMe : Screen(route = ContactMe_Route)
}