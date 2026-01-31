plugins {
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.publication")
}
kotlin {
    jvm()
    js(IR) {
        browser {
            testTask {
                enabled = false
            }
        }
        nodejs()
    }
    wasmJs {
        browser {
            testTask {
                enabled = false
            }
        }
        nodejs()
        d8()
    }
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

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlin.coroutines.test)
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

        @Suppress("UnusedPrivateProperty")
        val nonJsTest by creating {
            this.dependsOn(commonTest.get())
            sourceSets.toList()
                .filter { sourceSet -> sourceSet.name.endsWith("Test") }
                .filter { sourceSet -> sourceSet.name != wasmJsTest.name }
                .filter { sourceSet -> sourceSet.name != jsTest.name }
                .filter { sourceSet -> sourceSet.name != webTest.name }
                .filter { sourceSet -> sourceSet.name != commonTest.name }
                .onEach { sourceSet -> sourceSet.dependsOn(this) }
                .toList()
        }
    }
}
