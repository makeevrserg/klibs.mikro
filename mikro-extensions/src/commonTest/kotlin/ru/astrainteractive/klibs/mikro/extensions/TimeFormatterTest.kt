package ru.astrainteractive.klibs.mikro.extensions

import kotlin.time.Instant
import kotlin.time.TimeZone
import kotlin.test.Test
import kotlin.test.assertEquals

class TimeFormatterTest {
    private val timeFormatter: TimeFormatter
        get() = TimeFormatter("en_US")

    @Test
    fun test() {
        val instant = Instant.fromEpochMilliseconds(0)
        val timeZone = TimeZone.UTC
        timeFormatter.format(instant, "yyyy-MM-dd", timeZone).let { formatted ->
            assertEquals("1970-01-01", formatted)
        }
        timeFormatter.format(instant, "yyyy.MM.dd", timeZone).let { formatted ->
            assertEquals("1970.01.01", formatted)
        }
        timeFormatter.format(instant, "HH:mm:ss", timeZone).let { formatted ->
            assertEquals("00:00:00", formatted)
        }
    }
}
