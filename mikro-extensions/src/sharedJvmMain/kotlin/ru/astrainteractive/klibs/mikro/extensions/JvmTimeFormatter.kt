package ru.astrainteractive.klibs.mikro.extensions

import kotlinx.datetime.TimeZone
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone.getTimeZone
import kotlin.time.Instant

/**
 * Multiplatform ios/android time formatter
 */
class JvmTimeFormatter(private val locale: Locale = Locale.getDefault()) : TimeFormatter {
    override fun format(instant: Instant, format: String, timeZone: TimeZone): String {
        val sdf = SimpleDateFormat(format, locale)
        sdf.timeZone = getTimeZone(timeZone.id)

        val date = Date(instant.toEpochMilliseconds())
        return sdf.format(date)
    }
}
