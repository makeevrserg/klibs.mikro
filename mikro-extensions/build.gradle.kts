@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.publication")
}
kotlin {
    android {}
    jvm()
    js(IR) { browser { testTask { enabled = false } }; nodejs(); }
    wasmJs { browser { testTask { enabled = false } }; nodejs(); d8() }
    iosArm64()
    iosSimulatorArm64()
    iosX64()
//    linuxArm64()
//    linuxX64()
//    macosArm64()
//    macosX64()
//    mingwX64()
//    tvosArm64()
//    tvosSimulatorArm64()
//    tvosX64()
//    watchosArm64()
//    watchosSimulatorArm64()
//    watchosX64()
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
            implementation(libs.exposed.jdbc)
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
