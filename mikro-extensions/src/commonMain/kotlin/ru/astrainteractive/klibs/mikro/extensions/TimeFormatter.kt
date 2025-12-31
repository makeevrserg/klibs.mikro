package ru.astrainteractive.klibs.mikro.extensions

import kotlinx.datetime.TimeZone
import kotlin.time.Instant

/**
 * Multiplatform ios/android time formatter
 */
interface TimeFormatter {
    /**
     * This function will format time instant into format like YYYY-mm-dd or whatever
     *
     * Will throw exception if format is wrong
     */
    fun format(
        instant: Instant,
        format: String,
        timeZone: TimeZone = TimeZone.currentSystemDefault()
    ): String
}
