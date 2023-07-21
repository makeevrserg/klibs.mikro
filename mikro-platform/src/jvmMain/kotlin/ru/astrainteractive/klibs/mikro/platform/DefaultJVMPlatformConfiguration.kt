package ru.astrainteractive.klibs.mikro.platform

class DefaultJVMPlatformConfiguration : PlatformConfiguration {
    override val type: PlatformType
        get() = PlatformType.JVM
}
