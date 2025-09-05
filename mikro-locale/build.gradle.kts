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
    tvosX64()
    tvosArm64()
    tvosSimulatorArm64()
    watchosX64()
    watchosArm64()
    watchosSimulatorArm64()
    macosX64()
    macosArm64()

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
        }
        val jvmMain by getting
        val androidMain by getting
        val sharedJvmMain by creating
        sharedJvmMain.dependsOn(commonMain.get())
        jvmMain.dependsOn(sharedJvmMain)
        androidMain.dependsOn(sharedJvmMain)

        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.lokale"
}
