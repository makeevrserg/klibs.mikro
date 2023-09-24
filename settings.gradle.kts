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

// Project info
rootProject.name = "MiKro"

// Subprojects
include(":mikro-core")
include(":mikro-platform")
include(":mikro-validation")
