@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.platform.configuration

import android.content.Context
import ru.astrainteractive.klibs.mikro.platform.api.PlatformConfiguration
import ru.astrainteractive.klibs.mikro.platform.model.PlatformType

public class DefaultAndroidPlatformConfiguration(
    override val applicationContext: Context
) : PlatformConfiguration {
    override val type: PlatformType
        get() = PlatformType.ANDROID
}
