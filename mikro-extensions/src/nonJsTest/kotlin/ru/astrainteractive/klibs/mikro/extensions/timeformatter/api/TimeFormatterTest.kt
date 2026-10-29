@file:Suppress("TestMirror")

package ru.astrainteractive.klibs.mikro.extensions.timeformatter.api

import kotlinx.datetime.TimeZone
import ru.astrainteractive.klibs.mikro.extensions.timeformatter.fake.TimeFormatter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Instant

class TimeFormatterTest {
    private val timeFormatter: TimeFormatter
        get() = TimeFormatter("en_US")

    @Test
    fun GIVEN_epoch_instant_WHEN_format_with_patterns_THEN_returns_expected_text() {
        val instant = Instant.fromEpochMilliseconds(0)
        val timeZone = TimeZone.UTC
        timeFormatter.format(
            instant,
            "yyyy-MM-dd",
            timeZone
        ).let { formatted ->
            assertEquals("1970-01-01", formatted)
        }
        timeFormatter.format(
            instant,
            "yyyy.MM.dd",
            timeZone
        ).let { formatted ->
            assertEquals("1970.01.01", formatted)
        }
        timeFormatter.format(
            instant,
            "HH:mm:ss",
            timeZone
        ).let { formatted ->
            assertEquals("00:00:00", formatted)
        }
    }
}
