val elideVersion = "1.0-v3-alpha1-rc38"

plugins {
    kotlin("js") version "1.7.21"

    id("dev.elide.buildtools.plugin") version "1.0.0-beta12"
}

group = "io.koalaql"
version = "1.0-SNAPSHOT"

kotlin {
    js(IR) {
        browser {
            binaries.executable()
            commonWebpackConfig {
                sourceMaps = false
                cssSupport {
                    enabled = true
                }
                mode = org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig.Mode.DEVELOPMENT
            }
        }
    }
}

dependencies {
    implementation("dev.elide:base:$elideVersion")
    implementation(kotlin("stdlib-js"))
    implementation(libs.kotlinx.wrappers.browser)
    implementation(libs.kotlinx.wrappers.react)
    implementation(libs.kotlinx.wrappers.react.dom)
}

val assetDist by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

artifacts {
    add(assetDist.name, tasks.named("browserDistribution").map { it.outputs.files.files.single() })
}