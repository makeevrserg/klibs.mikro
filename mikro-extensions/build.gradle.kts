@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.publication")
    id("ru.astrainteractive.klibs.rules.gradle.linter")
    id("ru.astrainteractive.gradleplugin.rootinfo")
}
linter {
    unitKind.set("library")
    rootPackage.set("ru.astrainteractive.klibs.mikro")
}
kotlin {
    explicitApi()
    android {}
    jvm()
    js(IR) {
        browser { testTask { enabled = false } }
        nodejs()
    }
    wasmJs {
        browser { testTask { enabled = false } }
        nodejs()
        d8()
    }
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
            implementation(libs.kotlinx.serialization.json)
        }
        jvmTest.dependencies {
            implementation(libs.kotlin.coroutines.test)
            implementation(libs.h2)
        }

        val sharedJvmTest = create("sharedJvmTest") {
            this.dependsOn(commonTest.get())
            androidUnitTest.get().dependsOn(this)
            jvmTest.get().dependsOn(this)
        }
        create("nonJsTest") {
            this.dependsOn(commonTest.get())
            appleTest.get().dependsOn(this)
            sharedJvmTest.dependsOn(this)
        }
    }
}

tasks.withType<dev.detekt.gradle.Detekt>().configureEach {
    freeCompilerArgs.add("-Xexplicit-api=strict")
}

tasks.named<org.jetbrains.kotlin.gradle.targets.js.testing.KotlinJsTest>("wasmJsD8Test") {
    val jsJoda = rootProject.layout.buildDirectory.file("js/node_modules/@js-joda/core/dist/js-joda.esm.js")
    onTestFrameworkSet {
        val d8WorkingDir = workingDir
        doFirst {
            // D8 cannot resolve the bare @js-joda/core import emitted for kotlinx-datetime.
            val outputDirectory = d8WorkingDir.get().asFile
            val importObject = outputDirectory.listFiles().single { file -> file.name.endsWith(".import-object.mjs") }
            jsJoda.get().asFile.copyTo(outputDirectory.resolve("js-joda.esm.js"), overwrite = true)
            val importText = importObject.readText().replace("from '@js-joda/core'", "from './js-joda.esm.js'")
            importObject.writeText(importText)
        }
    }
}
