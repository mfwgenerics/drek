val elideVersion = "1.0-v3-alpha1-rc38"

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"

    id("dev.elide.buildtools.plugin") version "1.0.0-beta12"

    id("io.micronaut.application") version "3.6.5"
    id("io.micronaut.aot") version "3.6.5"
}

group = "io.koalaql"
version = "1.0-SNAPSHOT"

elide {
    server {
        assets {
            script("scripts.ui") {
                from(project(":frontend"))
            }
        }
    }
}

application {
    mainClass.set("sample.App")
}

micronaut {
    version.set(libs.versions.micronaut.lib.get())
    runtime.set(io.micronaut.gradle.MicronautRuntime.NETTY)
    processing {
        incremental.set(true)
        annotations.addAll(listOf("sample", "sample.*"))
    }
    aot {
        optimizeServiceLoading.set(true)
        convertYamlToJava.set(true)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        netty {
            enabled.set(true)
        }
    }
}

dependencies {
    kapt(libs.micronaut.inject.java)
    implementation(libs.elide.base)
    implementation(libs.elide.server)

    implementation(libs.micronaut.context)
    implementation(libs.micronaut.runtime)
    implementation(libs.kotlinx.html.jvm)
    implementation(libs.kotlinx.wrappers.css)

    runtimeOnly(libs.logback)
}

tasks.withType<Copy>().named("processResources") {
    dependsOn("copyStatic")
}

tasks.register<Copy>("copyStatic") {
    from("src/main/resources/static/**/*.*")
    into("$buildDir/resources/main/static")
}
