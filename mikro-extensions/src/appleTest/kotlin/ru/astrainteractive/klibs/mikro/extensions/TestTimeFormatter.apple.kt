package ru.astrainteractive.klibs.mikro.extensions

import platform.Foundation.NSLocale

actual fun TimeFormatter(code: String): TimeFormatter {
    return AppleTimeFormatter(NSLocale(code))
}
