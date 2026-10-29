package ru.astrainteractive.klibs.locale.util

import ru.astrainteractive.klibs.locale.api.LocaleProvider
import ru.astrainteractive.klibs.locale.provider.AppleLocaleProvider

public actual fun LocaleProvider(): LocaleProvider {
    return AppleLocaleProvider
}
