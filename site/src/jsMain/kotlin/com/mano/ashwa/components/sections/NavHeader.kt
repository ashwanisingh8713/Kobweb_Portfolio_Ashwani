package com.mano.ashwa.components.sections

import androidx.compose.runtime.*
import com.mano.ashwa.AppStyle.mobileFirstBreak
import com.mano.ashwa.AppStyle.mobileSecondBreak
import com.mano.ashwa.components.FontAwesomeType
import com.mano.ashwa.components.I
import com.mano.ashwa.components.P
import com.mano.ashwa.components.Tab
import com.mano.ashwa.components.tabs
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.CloseIcon
import com.varabyte.kobweb.silk.components.icons.HamburgerIcon
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.overlay.OverlayVars
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import com.mano.ashwa.components.widgets.IconButton
import com.mano.ashwa.data.REPO_LINK
import com.mano.ashwa.toSitePalette
import com.mano.ashwa.utils.linearGradient
import com.varabyte.kobweb.compose.css.BoxSizing
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.css.borderBottom
import com.varabyte.kobweb.compose.css.boxSizing
import com.varabyte.kobweb.compose.css.cursor
import com.varabyte.kobweb.compose.css.scale
import com.varabyte.kobweb.compose.css.whiteSpace
import com.varabyte.kobweb.compose.css.zIndex
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Header
import org.jetbrains.compose.web.dom.I
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.P

val NavHeaderStyle = CssStyle.base {
    Modifier.fillMaxWidth().padding(1.cssRem)
}

@Composable
private fun NavLink(path: String, text: String) {
    Link(path, text, variant = UndecoratedLinkVariant.then(UncoloredLinkVariant))
}

@Composable
private fun MenuItems() {
    NavLink("/", "Home")
    NavLink("/about", "About")
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState
    IconButton(onClick = { colorMode = colorMode.opposite },) {
        if (colorMode.isLight) MoonIcon() else SunIcon()
    }
    Tooltip(ElementTarget.PreviousSibling, "Toggle color mode", placement = PopupPlacement.BottomRight)
}

@Composable
private fun HamburgerButton(onClick: () -> Unit) {
    IconButton(onClick) {
        HamburgerIcon()
    }
}

@Composable
private fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick) {
        CloseIcon()
    }
}

val SideMenuSlideInAnim = Keyframes {
    from {
        Modifier.translateX(100.percent)
    }

    to {
        Modifier
    }
}

// Note: When the user closes the side menu, we don't immediately stop rendering it (at which point it would disappear
// abruptly). Instead, we start animating it out and only stop rendering it when the animation is complete.
enum class SideMenuState {
    CLOSED,
    OPEN,
    CLOSING;

    fun close() = when (this) {
        CLOSED -> CLOSED
        OPEN -> CLOSING
        CLOSING -> CLOSING
    }
}

@Composable
fun NavHeader() {
    Style(HeaderStyle)
    var open by mutableStateOf(false)

    Header({
        classes(HeaderStyle.navbar)
    }) {
        Nav({
            classes(HeaderStyle.navbarPart, HeaderStyle.navbarLinks)
            if (open) classes("open")
        }) {
            tabs.forEach {
                Tab(it) {
                    open = !open
                }
            }
        }

        A(REPO_LINK, {
            target(ATarget.Blank)
            classes(HeaderStyle.navbarPart, HeaderStyle.navbarGithub)
            title("GitHub")
            style {
                textDecoration("none")
            }
        }) {
            FaGithub()
            SpanText("Ashwani")
        }

        I({
            classes(HeaderStyle.mobileMenuButton, FontAwesomeType.SOLID.value, "fa-bars")

            onClick {
                open = !open
            }
        })
    }


}


object HeaderStyle : StyleSheet() {
    const val NAVBAR_COLOR = "#0A0A0F"
    const val NAVBAR_COLOR_SECONDARY = "#1A1225"
    const val NAVBAR_COLOR_SELECTED = "#2A1B3D"
    const val NAVBAR_ACCENT_START = "#00D4FF"
    const val NAVBAR_ACCENT_END = "#FF0080"
    val navbarHeight by variable<CSSNumeric>()

    init {
        root {
            navbarHeight(4.cssRem)
        }
    }

    val navbar by style {
        alignItems(AlignItems.Center)
        background(linearGradient(135.deg) {
            stop(Color(NAVBAR_COLOR))
            stop(Color(NAVBAR_COLOR_SECONDARY), 30.percent)
            stop(Color("#2A1B3D"), 70.percent)
            stop(Color(NAVBAR_COLOR_SECONDARY), 100.percent)
        })
        borderBottom {
            width(2.px)
            style(LineStyle.Solid)
            color(Color("transparent"))
        }
        property("border-image", "linear-gradient(90deg, $NAVBAR_ACCENT_START, $NAVBAR_ACCENT_END) 1")
        property("box-shadow", "0 2px 10px rgba(0, 0, 0, 0.3)")
        boxSizing(BoxSizing.BorderBox)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.SpaceBetween)
        position(Position.Fixed)
        height(navbarHeight.value())
        width(100.percent)
        top(0.px)
        zIndex(100)

        "i" style {
            fontSize(navbarHeight.value() * .5)
        }

        media(mediaMaxWidth(mobileSecondBreak)) {
            self {
                padding(0.px, 1.cssRem)
            }
        }
    }

    val navbarPart by style {
        alignItems(AlignItems.Center)
        color(Color.white)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val navbarLinks by style {
        "a" style {
            color(Color.white)
            textDecoration("none") // Remove underline from anchor tags
            display(DisplayStyle.InlineBlock)
            fontSize(0.95.cssRem)
            fontWeight(500)
            height(100.percent)
            lineHeight(navbarHeight.value())
            padding(0.px, clamp(1.3.cssRem, 2.5.vw, 2.3.cssRem))
            whiteSpace(WhiteSpace.NoWrap)
            position(Position.Relative)

            transitions {
                defaultDuration(0.3.s)
                properties("all")
            }

            before {
                property("content", "''")
                position(Position.Absolute)
                bottom(0.px)
                left(50.percent)
                width(0.px)
                height(2.px)
                background(linearGradient(90.deg) {
                    stop(Color(NAVBAR_ACCENT_START))
                    stop(Color(NAVBAR_ACCENT_END))
                })
                property("transform", "translateX(-50%)")
                borderRadius(1.px)
                transitions {
                    defaultDuration(0.3.s)
                    properties("width")
                }
            }

            after {
                property("content", "''")
                position(Position.Absolute)
                top(0.px)
                left(0.px)
                right(0.px)
                bottom(0.px)
                background(linearGradient(135.deg) {
                    stop(Color("rgba(0, 212, 255, 0.05)"))
                    stop(Color("rgba(255, 0, 128, 0.05)"))
                })
                opacity(0)
                borderRadius(0.3.cssRem)
                transitions {
                    defaultDuration(0.3.s)
                    properties("opacity")
                }
            }

            self + className("active") style {
                backgroundColor(Color("rgba(0, 212, 255, 0.08)"))

                before {
                    width(85.percent)
                    property("box-shadow", "0 0 4px rgba(0, 212, 255, 0.5)")
                }

                after {
                    opacity(1)
                }
            }

            hover(self) style {
                backgroundColor(Color("rgba(0, 212, 255, 0.05)"))

                before {
                    width(70.percent)
                }

                after {
                    opacity(0.6)
                }
            }
        }

        media(mediaMaxWidth(mobileSecondBreak)) {
            self {
                display(DisplayStyle.None)
                position(Position.Absolute)
                top(navbarHeight.value().unsafeCast<CSSLengthOrPercentageValue>())
                left(0.px)
                width(100.percent)

                background(linearGradient(180.deg) {
                    stop(Color(NAVBAR_COLOR))
                    stop(Color(NAVBAR_COLOR_SECONDARY))
                })
                property("box-shadow", "0 5px 15px rgba(0, 0, 0, 0.4)")

                self + className("open") style {
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    alignItems(AlignItems.Start)

                    "a" {
                        width(100.percent)
                        lineHeight(navbarHeight.value() * .8)
                        borderBottom {
                            width(1.px)
                            style(LineStyle.Solid)
                            color(Color("rgba(0, 212, 255, 0.15)"))
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val navbarGithub by style {
        borderRadius(.5.cssRem)
        gap(1.cssRem)
        height(80.percent)
        marginRight(1.vw)
        padding(.5.cssRem, 1.cssRem)
        border {
            width(1.px)
            style(LineStyle.Solid)
            color(Color("transparent"))
        }
        transitions {
            defaultDuration(0.3.s)
            properties("all")
        }
        background("""
			linear-gradient(${NAVBAR_COLOR}, ${NAVBAR_COLOR}) padding-box,
			linear-gradient(45deg, $NAVBAR_ACCENT_START, $NAVBAR_ACCENT_END) border-box
		""")

        "p" {
            fontWeight(600)
        }

        hover(self) style {
            property("box-shadow", "0 0 15px rgba(0, 212, 255, 0.3)")
            scale(1.02)
        }

        media(mediaMaxWidth(mobileFirstBreak)) {
            self {
                padding(.5.cssRem)
            }

            "p" {
                display(DisplayStyle.None)
            }
        }
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val mobileMenuButton by style {
        display(DisplayStyle.None)
        color(Color.white)
        cursor(Cursor.Pointer)
        fontSize(1.5.cssRem)
        padding(0.5.cssRem)
        borderRadius(0.3.cssRem)

        transitions {
            defaultDuration(0.3.s)
            properties("all")
        }

        hover(self) style {
            backgroundColor(Color("rgba(0, 212, 255, 0.1)"))
        }

        media(mediaMaxWidth(mobileSecondBreak)) {
            self {
                display(DisplayStyle.Block)
            }
        }
    }
}
