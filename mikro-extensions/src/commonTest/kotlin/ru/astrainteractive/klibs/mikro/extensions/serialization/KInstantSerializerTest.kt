package ru.astrainteractive.klibs.mikro.extensions.serialization

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class KInstantSerializerTest {

    @Test
    fun GIVEN_instant_WHEN_serialized_and_deserialized_THEN_equals_original() {
        val instant = Instant.fromEpochMilliseconds(1_700_000_000_000)
        val encoded = Json.encodeToString(KInstantSerializer, instant)
        val decoded = Json.decodeFromString(KInstantSerializer, encoded)
        assertEquals(instant, decoded)
    }

    @Test
    fun GIVEN_instant_WHEN_serialized_THEN_encoded_as_epoch_millis() {
        val instant = Instant.fromEpochMilliseconds(1_700_000_000_000)
        assertEquals("1700000000000", Json.encodeToString(KInstantSerializer, instant))
    }
}
