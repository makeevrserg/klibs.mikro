package ru.astrainteractive.klibs.mikro.platform

class DefaultNativePlatformConfiguration : PlatformConfiguration {
    override val type: PlatformType
        get() = PlatformType.NATIVE
}
