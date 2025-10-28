package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.styles.bannerStyle
import com.mano.ashwa.toSitePalette
import com.stevdza.san.kotlinbs.components.BSButton
import com.stevdza.san.kotlinbs.forms.BSInput
import com.stevdza.san.kotlinbs.forms.BSTextArea
import com.stevdza.san.kotlinbs.models.BSBorderRadius
import com.stevdza.san.kotlinbs.models.button.ButtonCustomization
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnDefaults
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.letterSpacing
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.common.SmoothColorStyle
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


@Composable
fun Footer() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
	Column(modifier = SmoothColorStyle.toModifier().fillMaxWidth().backgroundColor(sitePal.nearBackground),
//		verticalArrangement = Arrangement.Center,
		//horizontalAlignment = ColumnDefaults.HorizontalAlignment
	) {
		SpanText("Contact Me",
			modifier = Modifier.fontStyle(FontStyle.Normal).fontWeight(FontWeight.Bold)
				.align(alignment = Alignment.CenterHorizontally).padding(10.px).color(sitePal.pageTitleColor))

		var firstName by remember { mutableStateOf("") }
		var lastName by remember { mutableStateOf("") }

		ContactUsInput( firstName= firstName,
			lastName = lastName
//			onFirstName = { firstName = it },
//			onLastName = { lastName = it }
		)

		// Divider
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.height(2.px)
				.backgroundColor(Colors.LightGray)
		)

		// Quick Infos
		QuickInfos()

	}

}

@Composable
fun ContactUsInput(firstName: String, onFirstName: (String) -> Unit = {},
				   lastName: String, onLastName: (String) -> Unit = {}) {

	val fullWidth = 520.px
	val gap = 10.px
	val halfWidth = 250.px

	Column(
		// I want to center align all the content in this Column
		modifier = Modifier.fillMaxWidth().alignItems(AlignItems.Center)
	) {
		// First Name and Last Name side by side
		Row(
			modifier = Modifier
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			BSInput(
				modifier = Modifier.width(halfWidth),
				value = firstName,
				label = "First Name",
				placeholder = "First Name",
				onValueChange = {
					onFirstName(it)
				},
			)
			Box(modifier = Modifier.padding (gap))
			BSInput(
				modifier = Modifier.width(halfWidth),
				value = lastName,
				label = "Last Name",
				placeholder = "Last Name",
				onValueChange = {
					onLastName(it)
				},
			)
		}

		Box(modifier = Modifier.padding(gap))
		// Subject
		BSInput(
			modifier = Modifier.width(fullWidth),
			value = lastName,
			label = "Subject",
			placeholder = "Subject",
			onValueChange = {
				onLastName(it)
			},
		)

		Box(modifier = Modifier.padding(gap))
		// Message
		BSTextArea (
			modifier = Modifier.width(fullWidth),
			value = lastName,
			label = "Message",
			placeholder = "Message",
			onValueChange = {
				onLastName(it)
			}
		)

		Box(modifier = Modifier.padding(gap))
		// Send Button

			BSButton(
				modifier = Modifier.width(fullWidth-30.px)
					.justifyContent(JustifyContent.Center),
				text = "Send Message  ✉️",
				customization = ButtonCustomization(
					color = Colors.White,
					hoverColor = Colors.White,
					activeColor = Colors.WhiteSmoke,
					borderColor = Colors.White,
					hoverBorderColor = Colors.White,
					activeBorderColor = rgb(168, 192, 255),
					gradient = linearGradient(
						from = rgb(168, 192, 255),
						to = rgb(63, 43, 150),
						dir = LinearGradient.Direction.ToTopRight
					),
					borderRadius = BSBorderRadius(all = 50.px),
					horizontalPadding = 1.25.cssRem
				),
				onClick = {}
			)
		Box(modifier = Modifier.padding(gap))
	}
}

@Composable
fun QuickInfos() {
	Row(modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceEvenly,
		verticalAlignment = Alignment.Top // changed from CenterVertically to Top
	) {
		// Start ### My Projects-------------------------------
		Row(
			modifier = Modifier.fillMaxWidth().padding(20.px)
				.padding(20.px),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.Top // changed from CenterVertically to Top
		) {
			Column(
				horizontalAlignment = Alignment.Start,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.padding(10.px)
			) {
				H5 { Text("My Projects") }
				Column(
					horizontalAlignment = Alignment.Start,
					verticalArrangement = Arrangement.Center,
					modifier = Modifier.padding(10.px)
				) {
					A (
						href = "https://www.keysight.com/in/en/product/NTH50047B/nemo-handy-handheld-measurement-solution.html",
						attrs = {
							target(ATarget.Blank)
						}
					) {
						SpanText("Nemo Handy Handheld Measurement Solution", modifier = Modifier.padding(5.px))
					}
					A(
						href = "https://play.google.com/store/apps/details?id=com.mobstac.thehindu&h&pli=1",
						attrs = {
							target(ATarget.Blank)
						}
					) {
						SpanText(
							"The Hindu: India & World News",
							modifier = Modifier.padding(5.px)
						)
					}
					A(
						href = "https://play.google.com/store/apps/details?id=com.mobstac.thehindubusinessline",
						attrs = {
							target(ATarget.Blank)
						}
					) {
						SpanText(
							"The Hindu BusinessLine",
							modifier = Modifier.padding(5.px)
						)
					}
					SpanText("Shorts News", modifier = Modifier.padding(5.px))
					SpanText("KMP Shopify: Shopify Mobile Apps POC", modifier = Modifier.padding(5.px))
					SpanText("KMP Project : THE HINDU", modifier = Modifier.padding(5.px))
				}
			}
		}


		// Start ### Quick Links ### --------------------------------------
		Row(
			modifier = Modifier.fillMaxWidth().padding(20.px)
				.padding(20.px),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.Top // changed from CenterVertically to Top
		) {
			Column(
				horizontalAlignment = Alignment.Start,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.padding(10.px)
			) {
				H5 { Text("Quick Links") }
				SpanText("Home", modifier = Modifier.padding(5.px).textAlign(TextAlign.Start))
				SpanText("About Me", modifier = Modifier.padding(5.px))
				SpanText("Skills", modifier = Modifier.padding(5.px))
				SpanText("Experiences", modifier = Modifier.padding(5.px))
				SpanText("Portfolio", modifier = Modifier.padding(5.px))
				SpanText("Download CV", modifier = Modifier.padding(5.px))
			}
		}

		// Start ### Follow Me-------------------------------
		Row(
			modifier = Modifier.fillMaxWidth().padding(20.px)
				.padding(20.px),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.Top // changed from CenterVertically to Top
		) {
			Column(
				horizontalAlignment = Alignment.Start,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.padding(10.px)
			) {
				H5 { Text("Follow Me") }
				Div(
					attrs = Modifier
						.fillMaxWidth()
						.height(2.px)
						.backgroundColor(Colors.Blue)
						.toAttrs()
					)

				A (
					href = "https://github.com/ashwanisingh8713",
					attrs = {
						target(ATarget.Blank)
					}
				) {
					SpanText("GitHub", modifier = Modifier.padding(5.px))
				}
				A (
					href = "https://www.linkedin.com/in/ashwani-kumar-singh-45577042/",
					attrs = {
						target(ATarget.Blank)
					}
				) {
					SpanText("Linkedin", modifier = Modifier.padding(5.px))
				}

				A (
					href = "https://medium.com/@ashwanisingh8713",
					attrs = {
						target(ATarget.Blank)
					}
				) {
					SpanText("Medium", modifier = Modifier.padding(5.px))
				}
			}
		}
	}

}
