package ru.astrainteractive.klibs.mikro.extensions.serialization

import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class KUuidSerializerTest {

    private val raw = "550e8400-e29b-41d4-a716-446655440000"

    @Test
    fun GIVEN_uuid_WHEN_serialized_and_deserialized_THEN_equals_original() {
        val uuid = Uuid.parse(raw)
        val encoded = Json.encodeToString(KUuidSerializer, uuid)
        val decoded = Json.decodeFromString(KUuidSerializer, encoded)
        assertEquals(uuid, decoded)
    }

    @Test
    fun GIVEN_uuid_WHEN_serialized_THEN_encoded_as_quoted_string() {
        val uuid = Uuid.parse(raw)
        assertEquals("\"$raw\"", Json.encodeToString(KUuidSerializer, uuid))
    }
}
