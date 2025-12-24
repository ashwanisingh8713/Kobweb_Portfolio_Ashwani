package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mano.ashwa.LocalAppColorMode
import com.mano.ashwa.toSitePalette
import com.stevdza.san.kotlinbs.components.BSButton
import com.stevdza.san.kotlinbs.forms.BSInput
import com.stevdza.san.kotlinbs.forms.BSTextArea
import com.stevdza.san.kotlinbs.models.BSBorderRadius
import com.stevdza.san.kotlinbs.models.button.ButtonCustomization
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
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
import kotlinx.browser.window

// Generic validation message used across the form
private const val REQUIRED_MSG = "Required"


@Composable
fun Footer() {
    val current = LocalAppColorMode.current.value
    val sitePal = current.toSitePalette()
    val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

    // Theme-aware colors for Contact Me section
    val contactTitleColor = if (isLight) Color("#1E293B") else Colors.White
    val contactSubtitleColor = if (isLight) Color("#64748B") else Color("#94A3B8")
    val contactBgColor = if (isLight) Color("#F8FAFC") else Color("#0A0D12")

	Column(modifier = SmoothColorStyle.toModifier().fillMaxWidth().backgroundColor(sitePal.nearBackground)) {
		// Contact area (title + form) - Responsive with proper spacing
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.backgroundColor(contactBgColor)
				.padding(topBottom = 60.px, leftRight = 24.px),
			contentAlignment = Alignment.Center
		) {
			Column(
				modifier = Modifier.fillMaxWidth().maxWidth(800.px),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				// Decorative gradient line
				Div({
					style {
						width(60.px)
						height(4.px)
						property("background", "linear-gradient(90deg, #3C83EF, #7F52FF)")
						property("border-radius", "2px")
						property("margin-bottom", "20px")
					}
				})

				SpanText(
					"Get In Touch",
					modifier = Modifier
						.fontStyle(FontStyle.Normal)
						.fontWeight(FontWeight.Bold)
						.fontSize(32.px)
						.padding(bottom = 12.px)
						.textAlign(TextAlign.Center)
						.color(contactTitleColor)
				)
				SpanText(
					"Have a project in mind? Let's collaborate and build something amazing together.",
					modifier = Modifier
						.fontStyle(FontStyle.Normal)
						.fontSize(16.px)
						.padding(bottom = 32.px)
						.textAlign(TextAlign.Center)
						.color(contactSubtitleColor)
				)

				ContactUsInput()
			}
		}

		// Gradient Divider
		Div({
			style {
				width(100.percent)
				height(2.px)
				property("background", "linear-gradient(90deg, transparent, rgba(60, 131, 239, 0.5), rgba(127, 82, 255, 0.5), transparent)")
			}
		})

		// Quick Infos (keeps the site background)
		QuickInfos()

	}

}

@Composable
fun ContactUsInput() {
	val current = LocalAppColorMode.current.value
	val sitePal = current.toSitePalette()
	val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT

	// Theme-aware colors for form
	val cardBg = if (isLight) rgb(255, 255, 255) else rgb(13, 17, 23)
	val inputBg = if (isLight) rgb(248, 250, 252) else rgb(22, 27, 34)
	val labelColor = if (isLight) org.jetbrains.compose.web.css.Color("#1E293B") else Colors.White
	val placeholderColor = if (isLight) "#94a3b8" else "#6b7280"
	val errorColor = rgb(239, 68, 68)
	val infoColor = rgb(34, 197, 94)
	val inputBorderColor = if (isLight) "rgba(60, 131, 239, 0.2)" else "rgba(60, 131, 239, 0.3)"
	val inputFocusBorder = "rgba(60, 131, 239, 0.6)"
	val cardShadow = if (isLight) "0 4px 24px rgba(0, 0, 0, 0.08)" else "0 8px 32px rgba(0, 0, 0, 0.4)"
	val cardBorder = if (isLight) "1px solid rgba(60, 131, 239, 0.15)" else "1px solid rgba(60, 131, 239, 0.2)"

	// form state
	var firstName by remember { mutableStateOf("") }
	var lastName by remember { mutableStateOf("") }
	var email by remember { mutableStateOf("") }
	var subject by remember { mutableStateOf("") }
	var message by remember { mutableStateOf("") }

	// validation state
	var firstNameError by remember { mutableStateOf<String?>(null) }
	var lastNameError by remember { mutableStateOf<String?>(null) }
	var emailError by remember { mutableStateOf<String?>(null) }
	var subjectError by remember { mutableStateOf<String?>(null) }
	var messageError by remember { mutableStateOf<String?>(null) }
	var infoMessage by remember { mutableStateOf<String?>(null) }

	fun resetErrors() {
		firstNameError = null
		lastNameError = null
		emailError = null
		subjectError = null
		messageError = null
		infoMessage = null
	}

	fun isValidEmail(e: String): Boolean {
		val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
		return e.isNotBlank() && emailRegex.matches(e)
	}

	fun validateAll(): Boolean {
		resetErrors()
		var ok = true
		if (firstName.isBlank()) {
			firstNameError = REQUIRED_MSG
			ok = false
		}
		if (lastName.isBlank()) {
			lastNameError = REQUIRED_MSG
			ok = false
		}
		if (email.isBlank()) {
			emailError = REQUIRED_MSG
			ok = false
		} else if (!isValidEmail(email)) {
			emailError = "Invalid email"
			ok = false
		}
		if (subject.isBlank()) {
			subjectError = REQUIRED_MSG
			ok = false
		}
		if (message.isBlank()) {
			messageError = REQUIRED_MSG
			ok = false
		}
		return ok
	}

	// Responsive form container
	Div({
		style {
			width(100.percent)
			maxWidth(560.px)
			property("margin", "0 auto")
			property("border-radius", "20px")
			property("box-shadow", cardShadow)
			property("border", cardBorder)
			property("overflow", "hidden")
			property("backdrop-filter", "blur(10px)")
		}
	}) {
		Column(
			modifier = Modifier
				.backgroundColor(cardBg)
				.padding(32.px)
				.fillMaxWidth()
		) {
			// Name Row - Responsive (stacks on mobile)
			Div({
				style {
					display(DisplayStyle.Flex)
					flexWrap(FlexWrap.Wrap)
					gap(16.px)
					width(100.percent)
					property("margin-bottom", "20px")
				}
			}) {
				// First Name
				Div({
					style {
						flex(1)
						minWidth(200.px)
					}
				}) {
					FormField(
						label = "First Name",
						error = firstNameError,
						labelColor = labelColor,
						errorColor = errorColor
					) {
						StyledInput(
							value = firstName,
							placeholder = "",
							inputBg = inputBg,
							inputBorderColor = inputBorderColor,
							inputFocusBorder = inputFocusBorder,
							textColor = if (isLight) "#1e293b" else "#ffffff",
							placeholderColor = placeholderColor,
							onValueChange = { firstName = it }
						)
					}
				}

				// Last Name
				Div({
					style {
						flex(1)
						minWidth(200.px)
					}
				}) {
					FormField(
						label = "Last Name",
						error = lastNameError,
						labelColor = labelColor,
						errorColor = errorColor
					) {
						StyledInput(
							value = lastName,
							placeholder = "",
							inputBg = inputBg,
							inputBorderColor = inputBorderColor,
							inputFocusBorder = inputFocusBorder,
							textColor = if (isLight) "#1e293b" else "#ffffff",
							placeholderColor = placeholderColor,
							onValueChange = { lastName = it }
						)
					}
				}
			}

			// Email Field
			Div({
				style {
					width(100.percent)
					property("margin-bottom", "20px")
				}
			}) {
				FormField(
					label = "Email",
					error = emailError,
					labelColor = labelColor,
					errorColor = errorColor
				) {
					StyledInput(
						value = email,
						placeholder = "",
						inputBg = inputBg,
						inputBorderColor = inputBorderColor,
						inputFocusBorder = inputFocusBorder,
						textColor = if (isLight) "#1e293b" else "#ffffff",
						placeholderColor = placeholderColor,
						inputType = "email",
						onValueChange = { email = it }
					)
				}
			}

			// Subject Field
			Div({
				style {
					width(100.percent)
					property("margin-bottom", "20px")
				}
			}) {
				FormField(
					label = "Subject",
					error = subjectError,
					labelColor = labelColor,
					errorColor = errorColor
				) {
					StyledInput(
						value = subject,
						placeholder = "",
						inputBg = inputBg,
						inputBorderColor = inputBorderColor,
						inputFocusBorder = inputFocusBorder,
						textColor = if (isLight) "#1e293b" else "#ffffff",
						placeholderColor = placeholderColor,
						onValueChange = { subject = it }
					)
				}
			}

			// Message Field
			Div({
				style {
					width(100.percent)
					property("margin-bottom", "24px")
				}
			}) {
				FormField(
					label = "Message",
					error = messageError,
					labelColor = labelColor,
					errorColor = errorColor
				) {
					StyledTextArea(
						value = message,
						placeholder = "",
						inputBg = inputBg,
						inputBorderColor = inputBorderColor,
						inputFocusBorder = inputFocusBorder,
						textColor = if (isLight) "#1e293b" else "#ffffff",
						placeholderColor = placeholderColor,
						onValueChange = { message = it }
					)
				}
			}

			// Send Button
			Div({
				style {
					width(100.percent)
					display(DisplayStyle.Flex)
					justifyContent(JustifyContent.Center)
				}
			}) {
				Div({
					style {
						property("background", "linear-gradient(135deg, #3C83EF, #7F52FF)")
						property("border-radius", "50px")
						property("padding", "14px 40px")
						property("cursor", "pointer")
						property("transition", "all 0.3s ease")
						property("box-shadow", "0 4px 15px rgba(60, 131, 239, 0.3)")
					}
					onClick {
						if (!validateAll()) return@onClick
						val recipient = "ashwanisingh8713@gmail.com"
						val body = buildString {
							append("Name: $firstName $lastName\n")
							append("Email: $email\n\n")
							append(message)
						}
						val encSubject = window.asDynamic().encodeURIComponent(subject)
						val encBody = window.asDynamic().encodeURIComponent(body)
						val mailto = "mailto:$recipient?subject=$encSubject&body=$encBody"
						window.open(mailto)
						infoMessage = "Mail client opened successfully!"
					}
					onMouseEnter {
						it.currentTarget.asDynamic().style.transform = "translateY(-2px)"
						it.currentTarget.asDynamic().style.boxShadow = "0 6px 20px rgba(60, 131, 239, 0.4)"
					}
					onMouseLeave {
						it.currentTarget.asDynamic().style.transform = "translateY(0)"
						it.currentTarget.asDynamic().style.boxShadow = "0 4px 15px rgba(60, 131, 239, 0.3)"
					}
				}) {
					SpanText(
						"Send Message",
						modifier = Modifier
							.color(Colors.White)
							.fontWeight(FontWeight.SemiBold)
							.fontSize(16.px)
					)
				}
			}

			// Success message
			if (infoMessage != null) {
				SpanText(
					infoMessage!!,
					modifier = Modifier
						.padding(top = 16.px)
						.color(infoColor)
						.textAlign(TextAlign.Center)
						.fillMaxWidth()
				)
			}
		}
	}
}

@Composable
private fun FormField(
	label: String,
	error: String?,
	labelColor: org.jetbrains.compose.web.css.CSSColorValue,
	errorColor: org.jetbrains.compose.web.css.CSSColorValue,
	content: @Composable () -> Unit
) {
	Column(modifier = Modifier.fillMaxWidth()) {
		Row(
			modifier = Modifier.fillMaxWidth().padding(bottom = 8.px),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			SpanText(
				label,
				modifier = Modifier
					.fontWeight(FontWeight.Medium)
					.fontSize(14.px)
					.color(labelColor)
			)
			if (error != null) {
				SpanText(
					error,
					modifier = Modifier
						.fontSize(12.px)
						.color(errorColor)
				)
			}
		}
		content()
	}
}

@Composable
private fun StyledInput(
	value: String,
	placeholder: String,
	inputBg: org.jetbrains.compose.web.css.CSSColorValue,
	inputBorderColor: String,
	inputFocusBorder: String,
	textColor: String,
	placeholderColor: String,
	inputType: String = "text",
	onValueChange: (String) -> Unit
) {
	Input(if (inputType == "email") InputType.Email else InputType.Text) {
		value(value)
		placeholder(placeholder)
		onInput { onValueChange(it.value) }
		style {
			width(100.percent)
			property("padding", "12px 16px")
			property("border-radius", "12px")
			property("border", "1px solid $inputBorderColor")
			property("background-color", inputBg.toString())
			property("color", textColor)
			property("font-size", "15px")
			property("outline", "none")
			property("transition", "border-color 0.2s ease, box-shadow 0.2s ease")
		}
		onFocus {
			it.currentTarget.asDynamic().style.borderColor = inputFocusBorder
			it.currentTarget.asDynamic().style.boxShadow = "0 0 0 3px rgba(60, 131, 239, 0.1)"
		}
		onBlur {
			it.currentTarget.asDynamic().style.borderColor = inputBorderColor
			it.currentTarget.asDynamic().style.boxShadow = "none"
		}
	}
}

@Composable
private fun StyledTextArea(
	value: String,
	placeholder: String,
	inputBg: org.jetbrains.compose.web.css.CSSColorValue,
	inputBorderColor: String,
	inputFocusBorder: String,
	textColor: String,
	placeholderColor: String,
	onValueChange: (String) -> Unit
) {
	TextArea {
		value(value)
		placeholder(placeholder)
		onInput { onValueChange(it.value) }
		style {
			width(100.percent)
			height(120.px)
			property("padding", "12px 16px")
			property("border-radius", "12px")
			property("border", "1px solid $inputBorderColor")
			property("background-color", inputBg.toString())
			property("color", textColor)
			property("font-size", "15px")
			property("font-family", "inherit")
			property("outline", "none")
			property("resize", "vertical")
			property("min-height", "100px")
			property("transition", "border-color 0.2s ease, box-shadow 0.2s ease")
		}
		onFocus {
			it.currentTarget.asDynamic().style.borderColor = inputFocusBorder
			it.currentTarget.asDynamic().style.boxShadow = "0 0 0 3px rgba(60, 131, 239, 0.1)"
		}
		onBlur {
			it.currentTarget.asDynamic().style.borderColor = inputBorderColor
			it.currentTarget.asDynamic().style.boxShadow = "none"
		}
	}
}

@Composable
fun QuickInfos() {
	val current = LocalAppColorMode.current.value
	val sitePal = current.toSitePalette()
	val isLight = current == com.varabyte.kobweb.silk.theme.colors.ColorMode.LIGHT
	val textColor = sitePal.textColor
	val headingColor = sitePal.cardTitleColor

	Row(modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.SpaceEvenly,
		verticalAlignment = Alignment.Top
	) {
		// Start ### My Projects-------------------------------
		Row(
			modifier = Modifier.fillMaxWidth().padding(20.px)
				.padding(20.px),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.Top
		) {
			Column(
				horizontalAlignment = Alignment.Start,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.padding(10.px)
			) {
				H5(attrs = { style { color(if (isLight) org.jetbrains.compose.web.css.Color("#1e293b") else org.jetbrains.compose.web.css.Color.white) } }) { Text("My Projects") }
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
						SpanText("Nemo Handy Handheld Measurement Solution", modifier = Modifier.padding(5.px).color(sitePal.brand.primary))
					}
					A(
						href = "https://play.google.com/store/apps/details?id=com.mobstac.thehindu&h&pli=1",
						attrs = {
							target(ATarget.Blank)
						}
					) {
						SpanText(
							"The Hindu: India & World News",
							modifier = Modifier.padding(5.px).color(sitePal.brand.primary)
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
							modifier = Modifier.padding(5.px).color(sitePal.brand.primary)
						)
					}
					SpanText("Shorts News", modifier = Modifier.padding(5.px).color(textColor))
					SpanText("KMP Shopify: Shopify Mobile Apps POC", modifier = Modifier.padding(5.px).color(textColor))
					SpanText("KMP Project : THE HINDU", modifier = Modifier.padding(5.px).color(textColor))
				}
			}
		}


		// Start ### Quick Links ### --------------------------------------
		Row(
			modifier = Modifier.fillMaxWidth().padding(20.px)
				.padding(20.px),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.Top
		) {
			Column(
				horizontalAlignment = Alignment.Start,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.padding(10.px)
			) {
				H5(attrs = { style { color(if (isLight) org.jetbrains.compose.web.css.Color("#1e293b") else org.jetbrains.compose.web.css.Color.white) } }) { Text("Quick Links") }
				SpanText("Home", modifier = Modifier.padding(5.px).textAlign(TextAlign.Start).color(textColor))
				SpanText("About Me", modifier = Modifier.padding(5.px).color(textColor))
				SpanText("Skills", modifier = Modifier.padding(5.px).color(textColor))
				SpanText("Experiences", modifier = Modifier.padding(5.px).color(textColor))
				SpanText("Portfolio", modifier = Modifier.padding(5.px).color(textColor))
				SpanText("Download CV", modifier = Modifier.padding(5.px).color(textColor))
			}
		}

		// Start ### Follow Me-------------------------------
		Row(
			modifier = Modifier.fillMaxWidth().padding(20.px)
				.padding(20.px),
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.Top
		) {
			Column(
				horizontalAlignment = Alignment.Start,
				verticalArrangement = Arrangement.Center,
				modifier = Modifier.padding(10.px)
			) {
				H5(attrs = { style { color(if (isLight) org.jetbrains.compose.web.css.Color("#1e293b") else org.jetbrains.compose.web.css.Color.white) } }) { Text("Follow Me") }
				Div(
					attrs = Modifier
						.fillMaxWidth()
						.height(2.px)
						.backgroundColor(sitePal.brand.primary)
						.toAttrs()
					)

				A (
					href = "https://github.com/ashwanisingh8713",
					attrs = {
						target(ATarget.Blank)
					}
				) {
					SpanText("GitHub", modifier = Modifier.padding(5.px).color(sitePal.brand.primary))
				}
				A (
					href = "https://www.linkedin.com/in/ashwani-kumar-singh-45577042/",
					attrs = {
						target(ATarget.Blank)
					}
				) {
					SpanText("Linkedin", modifier = Modifier.padding(5.px).color(sitePal.brand.primary))
				}

				A (
					href = "https://medium.com/@ashwanisingh8713",
					attrs = {
						target(ATarget.Blank)
					}
				) {
					SpanText("Medium", modifier = Modifier.padding(5.px).color(sitePal.brand.primary))
				}
			}
		}
	}

}
