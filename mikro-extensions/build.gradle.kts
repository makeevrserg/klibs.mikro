@file:Suppress("UnusedPrivateMember")

plugins {
    kotlin("multiplatform")
    id("com.android.kotlin.multiplatform.library")
    alias(libs.plugins.kotlin.serialization)
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.publication")
    id("ru.astrainteractive.gradleplugin.android.namespace")
}

kotlin {
    jvm()
    androidLibrary {}
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.arkivanov.decompose)
            implementation(libs.kotlinx.datetime)
            implementation(libs.moko.resources)
            // Local
            implementation(projects.mikroCore)
        }
        jvmMain.dependencies {
            implementation(libs.exposed.core)
            implementation(libs.exposed.dao)
        }
        create("sharedJvmMain") {
            this.dependsOn(commonMain.get())
            androidMain.get().dependsOn(this)
            jvmMain.get().dependsOn(this)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        create("sharedJvmTest") {
            this.dependsOn(commonTest.get())
            androidUnitTest.get().dependsOn(this)
            jvmTest.get().dependsOn(this)
        }
    }
}
