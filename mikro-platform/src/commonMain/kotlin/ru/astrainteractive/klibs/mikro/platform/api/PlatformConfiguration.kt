package ru.astrainteractive.klibs.mikro.platform.api

import ru.astrainteractive.klibs.mikro.platform.model.PlatformType

public expect interface PlatformConfiguration {
    public val type: PlatformType
}
