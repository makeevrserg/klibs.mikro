package ru.astrainteractive.klibs.mikro.platform

import android.content.Context

class DefaultAndroidPlatformConfiguration(
    override val applicationContext: Context
) : PlatformConfiguration {
    override val type: PlatformType
        get() = PlatformType.ANDROID
}
