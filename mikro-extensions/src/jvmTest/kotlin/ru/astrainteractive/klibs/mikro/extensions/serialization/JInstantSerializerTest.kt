package ru.astrainteractive.klibs.mikro.extensions.serialization

import kotlinx.serialization.json.Json
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class JInstantSerializerTest {

    @Test
    fun GIVEN_instant_WHEN_serialized_and_deserialized_THEN_equals_original() {
        val instant = Instant.ofEpochMilli(1_700_000_000_000)
        val encoded = Json.encodeToString(JInstantSerializer, instant)
        val decoded = Json.decodeFromString(JInstantSerializer, encoded)
        assertEquals(instant, decoded)
    }

    @Test
    fun GIVEN_instant_WHEN_serialized_THEN_encoded_as_epoch_millis() {
        val instant = Instant.ofEpochMilli(1_700_000_000_000)
        assertEquals("1700000000000", Json.encodeToString(JInstantSerializer, instant))
    }
}
