import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.8.0"
    kotlin("plugin.jpa") version "1.8.0"
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    targetHierarchy.default()

    jvm("desktop")
    
    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }

        val springMain by creating {
            dependencies {
                implementation("org.springframework.boot:spring-boot-starter")
                implementation("org.springframework.boot:spring-boot-starter-web")
                implementation("org.springframework.boot:spring-boot-starter-data-jpa")
                implementation("org.springframework.boot:spring-boot-starter-cache")
                implementation("com.h2database:h2")
            }
            kotlin.srcDir("src/springMain/kotlin")
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
            dependsOn(commonMain)
            dependsOn(springMain)
        }


    }
}


compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "me.time"
            packageVersion = "1.0.0"
        }
    }
}
