pluginManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenLocal()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Project info
rootProject.name = "MiKro"

// Subprojects
include(":mikro-core")
include(":mikro-platform")
include(":mikro-validation")
include(":mikro-locale")
include(":mikro-extensions")
