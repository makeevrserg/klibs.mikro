package ru.astrainteractive.klibs.mikro.platform.api

import ru.astrainteractive.klibs.mikro.platform.model.PlatformType

public actual interface PlatformConfiguration {
    public actual val type: PlatformType
}
