plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.publication")
    id("ru.astrainteractive.gradleplugin.detekt")
    id("ru.astrainteractive.gradleplugin.rootinfo")
}
kotlin {
    android {}
    jvm()
    js(IR) { browser { testTask { enabled = false } }; nodejs(); }
    wasmJs { browser { testTask { enabled = false } }; nodejs(); d8() }
    iosArm64()
    iosSimulatorArm64()
    iosX64()
    linuxArm64()
    linuxX64()
    macosArm64()
    macosX64()
    mingwX64()
    tvosArm64()
    tvosSimulatorArm64()
    tvosX64()
    watchosArm64()
    watchosSimulatorArm64()
    watchosX64()
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
