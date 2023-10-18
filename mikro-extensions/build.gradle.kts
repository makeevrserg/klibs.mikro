@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
kotlin {
    targetHierarchy.default()
    jvm()
    androidTarget {
        publishLibraryVariants("release", "debug")
        publishLibraryVariantsGroupedByFlavor = true
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    sourceSets {
        /* Main source sets */
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
                implementation("com.arkivanov.decompose:decompose:2.0.1")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("dev.icerock.moko:resources:0.23.0")
            }
        }
        val androidMain by getting
        val jvmMain by getting
        val sharedJvmMain by creating {
            this.dependsOn(commonMain)
            androidMain.dependsOn(this)
            jvmMain.dependsOn(this)
        }

        /* Test source sets */
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidUnitTest by getting
        val jvmTest by getting
        val sharedJvmTest by creating {
            this.dependsOn(commonTest)
            androidUnitTest.dependsOn(this)
            jvmTest.dependsOn(this)
        }
    }
}

android {
    namespace = "${projectInfo.group}.mikro.extensions"
}
