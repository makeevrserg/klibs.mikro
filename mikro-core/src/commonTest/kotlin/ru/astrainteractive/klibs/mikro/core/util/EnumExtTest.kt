package ru.astrainteractive.klibs.mikro.core.util

import kotlin.test.Test
import kotlin.test.assertEquals

class EnumExtTest {
    private enum class Direction { NORTH, EAST, SOUTH, WEST }

    private val values = enumValues<Direction>()

    @Test
    fun GIVEN_middle_value_WHEN_next_THEN_returns_following_value() {
        assertEquals(Direction.EAST, Direction.NORTH.next(values))
        assertEquals(Direction.SOUTH, Direction.EAST.next(values))
    }

    @Test
    fun GIVEN_last_value_WHEN_next_THEN_wraps_to_first() {
        assertEquals(Direction.NORTH, Direction.WEST.next(values))
    }

    @Test
    fun GIVEN_middle_value_WHEN_prev_THEN_returns_preceding_value() {
        assertEquals(Direction.NORTH, Direction.EAST.prev(values))
        assertEquals(Direction.EAST, Direction.SOUTH.prev(values))
    }

    @Test
    fun GIVEN_first_value_WHEN_prev_THEN_wraps_to_last() {
        assertEquals(Direction.WEST, Direction.NORTH.prev(values))
    }

    @Test
    fun GIVEN_offset_equal_to_size_WHEN_addIndex_THEN_returns_same_value() {
        assertEquals(Direction.NORTH, Direction.NORTH.addIndex(values.size, values))
    }

    @Test
    fun GIVEN_offset_larger_than_size_WHEN_addIndex_THEN_wraps_modulo() {
        assertEquals(Direction.EAST, Direction.NORTH.addIndex(values.size + 1, values))
    }

    @Test
    fun GIVEN_any_value_WHEN_next_applied_size_times_THEN_returns_to_start() {
        var current = Direction.EAST
        repeat(values.size) { current = current.next(values) }
        assertEquals(Direction.EAST, current)
    }

    @Test
    fun GIVEN_next_then_prev_WHEN_combined_THEN_returns_original_value() {
        assertEquals(Direction.SOUTH, Direction.SOUTH.next(values).prev(values))
    }
}
