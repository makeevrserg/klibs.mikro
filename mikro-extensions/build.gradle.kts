@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo


plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
kotlin {
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
    applyDefaultHierarchyTemplate()

    sourceSets {
        /* Main source sets */
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.arkivanov.decompose)
                implementation(libs.kotlinx.datetime)
                implementation(libs.moko.resources)
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
    namespace = "${requireProjectInfo.group}.mikro.extensions"
}
