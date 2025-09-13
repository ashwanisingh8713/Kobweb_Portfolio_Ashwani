import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.HEAD
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.unsafe
import com.varabyte.kobweb.common.text.ensureSurrounded
import com.varabyte.kobweb.common.text.splitCamelCase
import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import com.varabyte.kobwebx.gradle.markdown.children
import kotlinx.html.*
import org.commonmark.node.Text
import java.net.HttpURLConnection
import java.net.URI

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    //alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

fun HEAD.meta(property: String, content: String) {
    meta {
        attributes["property"] = property
        this.content = content
    }
}

group = "com.mano.ashwa"
version = "1.0-SNAPSHOT"

kobweb {
    app {
//  #      index {
// #           description.set("Powered by Kobweb")
//   #     }
        index {
            val url = "https://ayfri.com"
            val authorStr = "Pierre Roy"
            val twitterHandle = "@Ayfri_"

            val descriptionStr = """
				Hi, I'm Pierre Roy, an IT student, and I'm passionate about computer science and especially programming.
				Discover my projects and my blog on this website.
			""".trimIndent()

            val image = "$url/images/avatar.webp"

            description.set(descriptionStr)

            globals.put("author", authorStr)
            globals.put("description", descriptionStr)
            globals.put("url", url)

            faviconPath = "/logo.png"

            // Remove head.apply { add { ... } } block for configuration cache compatibility
            // If you need to add custom meta/link/script tags, use supported properties or static resources
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("ashwa" /*, includeServer = true*/)

    sourceSets {
//        commonMain.dependencies {
//          // Add shared dependencies between JS and JVM here if building a fullstack app
//        }

        jsMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            // This default template uses built-in SVG icons, but what's available is limited.
            // Uncomment the following if you want access to a large set of font-awesome icons:
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            implementation(libs.kotlin.bootstrap)
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
//        jvmMain.dependencies {
//            compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
//        }
    }
}
