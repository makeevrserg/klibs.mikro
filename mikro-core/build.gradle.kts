import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
kotlin {
    jvm()
    androidTarget()
    js(IR) {
        browser()
        nodejs()
    }
    wasmJs()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()
    watchosX64()
    watchosArm64()
    watchosSimulatorArm64()
    linuxX64()
    macosX64()
    macosArm64()
    mingwX64()

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
        }

        jvmMain.dependencies {
            implementation(libs.logback.classic)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        val wasmJsMain by getting
        val jsMain by getting
        val webMain by getting

        @Suppress("UnusedPrivateProperty")
        val nonJsMain by creating {
            this.dependsOn(commonMain.get())
            sourceSets.toList()
                .filter { sourceSet -> sourceSet.name.endsWith("Main") }
                .filter { sourceSet -> sourceSet.name != wasmJsMain.name }
                .filter { sourceSet -> sourceSet.name != jsMain.name }
                .filter { sourceSet -> sourceSet.name != webMain.name }
                .filter { sourceSet -> sourceSet.name != commonMain.name }
                .onEach { sourceSet -> sourceSet.dependsOn(this) }
                .toList()
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.mikro.core"
}
