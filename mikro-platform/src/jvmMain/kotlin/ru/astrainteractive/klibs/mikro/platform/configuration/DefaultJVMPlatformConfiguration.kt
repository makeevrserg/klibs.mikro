@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.platform.configuration

import ru.astrainteractive.klibs.mikro.platform.api.PlatformConfiguration
import ru.astrainteractive.klibs.mikro.platform.model.PlatformType

public class DefaultJVMPlatformConfiguration : PlatformConfiguration {
    override val type: PlatformType
        get() = PlatformType.JVM
}
