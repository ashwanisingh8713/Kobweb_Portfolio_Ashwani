package com.mano.ashwa

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color

/**
 * @property nearBackground A useful color to apply to a container that should differentiate itself from the background
 *   but just a little.
 */
class SitePalette(
    val brand: Brand,
    val nearBackground: Color,
    val cobweb: Color,
    val cardColor: Color,
    val cardTitleColor: Color,
    val cardSubTitleColor: Color,
    val cardDescriptionColor: Color,
    val textColor: Color,
    val pageTitleColor: Color
) {
    class Brand(
        val primary: Color = Color.rgb(0x3C83EF),
        val accent: Color = Color.rgb(0xF3DB5B),
    )
}

object SitePalettes {
    val light = SitePalette(
        nearBackground = Color.rgb(0xF4F6FA),
        cobweb = Colors.LightGray,
        brand = SitePalette.Brand(primary = Color.rgb(0x3C83EF), accent = Color.rgb(0xFCBA03)),
        cardColor = Color.rgb(0xFFFFFF),
        cardTitleColor = Color.rgb(0x0F172A),
        cardSubTitleColor = Color.rgb(0x4B5563),
        cardDescriptionColor = Color.rgb(0x6B7280),
        pageTitleColor = Color.rgb(0x6B7280),
        textColor = Color.rgb(0x6B7280),
    )

    val dark = SitePalette(
        nearBackground = Color.rgb(0x06080B),
        cobweb = Colors.LightGray.inverted(),
        brand = SitePalette.Brand(primary = Color.rgb(0x3C83EF), accent = Color.rgb(0xF3DB5B)),
        cardColor = Color.rgb(0x0B1220),
        cardTitleColor = Color.rgb(0xE6EEF8),
        cardSubTitleColor = Color.rgb(0xAAB7C9),
        cardDescriptionColor = Color.rgb(0x94A3B8),
        pageTitleColor = Color.rgb(0x94A3B8),
        textColor = Color.rgb(0x94A3B8)
    )
}

fun ColorMode.toSitePalette(): SitePalette {
    return when (this) {
        ColorMode.LIGHT -> SitePalettes.light
        ColorMode.DARK -> SitePalettes.dark
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    ctx.theme.palettes.light.background = Color.rgb(0xFAFAFA)
    ctx.theme.palettes.light.color = Colors.Black
    ctx.theme.palettes.dark.background = Color.rgb(0x06080B)
    ctx.theme.palettes.dark.color = Colors.White
}
