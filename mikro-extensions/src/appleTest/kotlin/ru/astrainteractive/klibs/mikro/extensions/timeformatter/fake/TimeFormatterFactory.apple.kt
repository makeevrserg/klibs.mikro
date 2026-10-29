package ru.astrainteractive.klibs.mikro.extensions.timeformatter.fake

import platform.Foundation.NSLocale
import ru.astrainteractive.klibs.mikro.extensions.timeformatter.api.TimeFormatter
import ru.astrainteractive.klibs.mikro.extensions.timeformatter.formatter.AppleTimeFormatter

actual fun TimeFormatter(code: String): TimeFormatter {
    return AppleTimeFormatter(NSLocale(code))
}
