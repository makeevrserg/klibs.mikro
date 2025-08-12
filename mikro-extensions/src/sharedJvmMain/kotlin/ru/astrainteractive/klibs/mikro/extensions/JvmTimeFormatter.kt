package ru.astrainteractive.klibs.mikro.extensions

import kotlin.time.Instant
import kotlin.time.TimeZone
import kotlin.time.toJavaLocalDateTime
import kotlin.time.toLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * Multiplatform ios/android time formatter
 */
class JvmTimeFormatter(private val locale: Locale = Locale.getDefault()) : TimeFormatter {
    override fun format(instant: Instant, format: String, timeZone: TimeZone): String {
        val lDateTime = instant.toLocalDateTime(timeZone)
        val jlDateTime = lDateTime.toJavaLocalDateTime()
        val formatter = DateTimeFormatter.ofPattern(format).withLocale(locale)
        return jlDateTime.format(formatter)
    }
}
