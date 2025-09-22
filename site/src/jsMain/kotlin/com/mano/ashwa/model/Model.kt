package com.mano.ashwa.model

import com.varabyte.kobweb.compose.ui.graphics.Color

data class SkillData(val title: String, val skills: List<String>, val icon: String, val color: Color)
data class ExperienceData(val title: String, val subTitle: String, val skills: List<String>, val icon: String, val color: Color)
