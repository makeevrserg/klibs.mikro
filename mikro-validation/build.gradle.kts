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
        /* Main source sets */
        @Suppress("UnusedPrivateProperty")
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.coroutines.core)
            }
        }

        /* Test source sets */
        @Suppress("UnusedPrivateProperty")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "${requireProjectInfo.group}.validation"
}
afterEvaluate {
    tasks
        .withType<AbstractPublishToMaven>()
        .forEach { publishTask ->
            tasks.withType<Sign>().forEach(publishTask::mustRunAfter)
        }
}
