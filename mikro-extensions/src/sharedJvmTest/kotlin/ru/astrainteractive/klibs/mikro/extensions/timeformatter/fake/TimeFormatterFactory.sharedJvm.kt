package ru.astrainteractive.klibs.mikro.extensions.timeformatter.fake

import ru.astrainteractive.klibs.mikro.extensions.timeformatter.api.TimeFormatter
import ru.astrainteractive.klibs.mikro.extensions.timeformatter.formatter.JvmTimeFormatter
import java.util.Locale

actual fun TimeFormatter(code: String): TimeFormatter {
    return JvmTimeFormatter(Locale(code))
}
