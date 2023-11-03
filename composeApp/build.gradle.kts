import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("idea")
    kotlin("plugin.spring") version "1.8.0"
    kotlin("plugin.jpa") version "1.8.0"
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    targetHierarchy.default()

    jvm("desktop")
    
    sourceSets {
        KotlinSourceSet
        val commonMain by getting {
            val ktor_version: String by project
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation("org.springframework.boot:spring-boot-starter")
                implementation("org.springframework.boot:spring-boot-starter-web")
                implementation("org.springframework.boot:spring-boot-starter-data-jpa")
                implementation("org.springframework.boot:spring-boot-starter-cache")
                implementation("com.h2database:h2")
                implementation ("org.jetbrains.kotlin:kotlin-reflect")
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-cio:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
            }
        }

        val commonTest by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("org.springframework.boot:spring-boot-starter-test")
                implementation("org.springframework:spring-test")
                implementation(kotlin("test-junit"))
            }
            kotlin.srcDir("src/springTest/kotlin")
            resources.srcDir("src/springTest/resources")
            tasks.withType<Test> {
                useJUnitPlatform()
            }
        }



        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
            dependsOn(commonMain)
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
