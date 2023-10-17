import ru.astrainteractive.gradleplugin.util.ProjectProperties.projectInfo

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

    sourceSets {
        /* Main source sets */
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
            }
        }
        val nativeMain by creating
        val jvmMain by getting
        val androidMain by getting
        val sharedJvmMain by creating
        val appleMain by creating
        val iosMain by creating
        val tvosMain by creating
        val watchosMain by creating
        val macosMain by creating
        val windowsMain by creating
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val tvosX64Main by getting
        val tvosArm64Main by getting
        val tvosSimulatorArm64Main by getting
        val watchosX64Main by getting
        val watchosArm64Main by getting
        val watchosSimulatorArm64Main by getting
        val macosX64Main by getting
        val macosArm64Main by getting

        /* Main hierarchy */
        nativeMain.dependsOn(commonMain)
        sharedJvmMain.dependsOn(commonMain)
        jvmMain.dependsOn(sharedJvmMain)
        androidMain.dependsOn(sharedJvmMain)
        appleMain.dependsOn(nativeMain)
        iosMain.dependsOn(appleMain)
        iosX64Main.dependsOn(iosMain)
        iosArm64Main.dependsOn(iosMain)
        iosSimulatorArm64Main.dependsOn(iosMain)
        tvosMain.dependsOn(appleMain)
        tvosX64Main.dependsOn(tvosMain)
        tvosArm64Main.dependsOn(tvosMain)
        tvosSimulatorArm64Main.dependsOn(tvosMain)
        watchosMain.dependsOn(appleMain)
        watchosX64Main.dependsOn(watchosMain)
        watchosArm64Main.dependsOn(watchosMain)
        watchosSimulatorArm64Main.dependsOn(watchosMain)
        macosMain.dependsOn(appleMain)
        macosX64Main.dependsOn(macosMain)
        macosArm64Main.dependsOn(macosMain)
        windowsMain.dependsOn(nativeMain)

        /* Test source sets */
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val nativeTest by creating
        val jvmTest by getting
        val androidUnitTest by getting
        val sharedJvmTest by creating
        val appleTest by creating
        val iosTest by creating
        val tvosTest by creating
        val watchosTest by creating
        val macosTest by creating
        val windowsTest by creating
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val tvosX64Test by getting
        val tvosArm64Test by getting
        val tvosSimulatorArm64Test by getting
        val watchosX64Test by getting
        val watchosArm64Test by getting
        val watchosSimulatorArm64Test by getting
        val macosX64Test by getting
        val macosArm64Test by getting

        /* Test hierarchy */
        nativeTest.dependsOn(commonTest)
        sharedJvmTest.dependsOn(commonTest)
        jvmTest.dependsOn(sharedJvmTest)
        androidUnitTest.dependsOn(sharedJvmTest)
        appleTest.dependsOn(commonTest)
        iosTest.dependsOn(appleTest)
        iosX64Test.dependsOn(iosTest)
        iosArm64Test.dependsOn(iosTest)
        iosSimulatorArm64Test.dependsOn(iosTest)
        tvosTest.dependsOn(appleTest)
        tvosX64Test.dependsOn(tvosTest)
        tvosArm64Test.dependsOn(tvosTest)
        tvosSimulatorArm64Test.dependsOn(tvosTest)
        watchosTest.dependsOn(appleTest)
        watchosX64Test.dependsOn(watchosTest)
        watchosArm64Test.dependsOn(watchosTest)
        watchosSimulatorArm64Test.dependsOn(watchosTest)
        macosTest.dependsOn(appleTest)
        macosX64Test.dependsOn(macosTest)
        macosArm64Test.dependsOn(macosTest)
        windowsTest.dependsOn(nativeTest)
    }
}

android {
    namespace = "${projectInfo.group}.lokale"
}
