package ru.astrainteractive.klibs.mikro.extensions.serialization

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class DurationSerializerTest {

    @Test
    fun GIVEN_full_duration_WHEN_fromDuration_THEN_matches_documented_format() {
        val duration = 25.days + 6.hours + 10.minutes + 30.seconds
        assertEquals("3w4d6h10m30s", DurationSerializer.fromDuration(duration))
    }

    @Test
    fun GIVEN_documented_string_WHEN_toDuration_THEN_parses_each_unit() {
        val expected = (3 * 7).days + 4.days + 6.hours + 10.minutes + 30.seconds
        assertEquals(expected, DurationSerializer.toDuration("3w4d6h10m30s"))
    }

    @Test
    fun GIVEN_durations_WHEN_round_tripped_THEN_value_is_preserved() {
        val samples = listOf(
            0.seconds,
            30.seconds,
            90.minutes,
            23.hours + 59.minutes + 59.seconds,
            1.days,
            7.days,
            8.days,
            25.days + 6.hours + 10.minutes + 30.seconds
        )
        samples.forEach { duration ->
            val serialized = DurationSerializer.fromDuration(duration)
            val restored = DurationSerializer.toDuration(serialized)
            assertEquals(duration, restored, "round trip failed for $duration (serialized as '$serialized')")
        }
    }

    @Test
    fun GIVEN_single_day_WHEN_round_tripped_THEN_stays_one_day_not_one_week() {
        val serialized = DurationSerializer.fromDuration(1.days)
        assertEquals(1.days, DurationSerializer.toDuration(serialized))
    }

    @Test
    fun GIVEN_zero_duration_WHEN_round_tripped_THEN_stays_zero() {
        assertEquals(Duration.ZERO, DurationSerializer.toDuration(DurationSerializer.fromDuration(Duration.ZERO)))
    }

    @Test
    fun GIVEN_string_without_any_unit_WHEN_toDuration_THEN_fails() {
        assertFailsWith<IllegalStateException> { DurationSerializer.toDuration("123") }
    }

    @Test
    fun GIVEN_unit_without_number_WHEN_toDuration_THEN_fails() {
        assertFailsWith<IllegalStateException> { DurationSerializer.toDuration("xd") }
    }
}
