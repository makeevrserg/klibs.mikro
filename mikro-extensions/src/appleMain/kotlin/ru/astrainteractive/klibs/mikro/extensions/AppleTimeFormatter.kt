package ru.astrainteractive.klibs.mikro.extensions

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toNSDate
import kotlinx.datetime.toNSTimeZone
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

/**
 * Multiplatform ios/android time formatter
 */
class AppleTimeFormatter(
    private val locale: NSLocale = NSLocale.currentLocale
) : TimeFormatter {
    override fun format(instant: Instant, format: String, timeZone: TimeZone): String {
        val dateFormatter = NSDateFormatter().apply {
            this.dateFormat = format
            this.timeZone = timeZone.toNSTimeZone()
            this.locale = this@AppleTimeFormatter.locale
        }
        val nsDate = instant.toNSDate()
        return dateFormatter.stringFromDate(nsDate)
    }
}
