package ru.astrainteractive.klibs.mikro.extensions.serialization.serializer

import kotlinx.serialization.json.Json
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

class JUuidSerializerTest {

    @Test
    fun GIVEN_uuid_WHEN_serialized_and_deserialized_THEN_equals_original() {
        val uuid = UUID.fromString(RAW)
        val encoded = Json.encodeToString(JUuidSerializer, uuid)
        val decoded = Json.decodeFromString(JUuidSerializer, encoded)
        assertEquals(uuid, decoded)
    }

    @Test
    fun GIVEN_uuid_WHEN_serialized_THEN_encoded_as_quoted_string() {
        val uuid = UUID.fromString(RAW)
        assertEquals("\"$RAW\"", Json.encodeToString(JUuidSerializer, uuid))
    }

    companion object {
        private const val RAW = "550e8400-e29b-41d4-a716-446655440000"
    }
}
