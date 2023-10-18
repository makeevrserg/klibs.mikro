package ru.astrainteractive.klibs.mikro.extensions

import java.util.Locale

actual fun TimeFormatter(code: String): TimeFormatter {
    return JvmTimeFormatter(Locale(code))
}
