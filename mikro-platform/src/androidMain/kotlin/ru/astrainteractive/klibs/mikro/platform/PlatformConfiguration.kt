package ru.astrainteractive.klibs.mikro.platform

import android.content.Context

actual interface PlatformConfiguration {
    val applicationContext: Context
    actual val type: PlatformType
}
