@file:Suppress("MatchingDeclarationName", "PlatformImport")

package ru.astrainteractive.klibs.mikro.platform.api

import android.content.Context
import ru.astrainteractive.klibs.mikro.platform.model.PlatformType

public actual interface PlatformConfiguration {
    public val applicationContext: Context
    public actual val type: PlatformType
}
