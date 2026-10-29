package ru.astrainteractive.klibs.mikro.core.util

import kotlin.test.Test
import kotlin.test.assertEquals

class SortedByExtTest {
    private val words = listOf(
        "aaa",
        "a",
        "aa"
    )

    @Test
    fun GIVEN_ascending_true_WHEN_sorted_by_THEN_ordered_by_selected_key_ascending() {
        val result = words.sortedBy(isAscending = true, String::length)
        assertEquals(
            listOf(
                "a",
                "aa",
                "aaa"
            ),
            result
        )
    }

    @Test
    fun GIVEN_ascending_false_WHEN_sorted_by_THEN_ordered_by_selected_key_descending() {
        val result = words.sortedBy(isAscending = false, String::length)
        assertEquals(
            listOf(
                "aaa",
                "aa",
                "a"
            ),
            result
        )
    }
}
