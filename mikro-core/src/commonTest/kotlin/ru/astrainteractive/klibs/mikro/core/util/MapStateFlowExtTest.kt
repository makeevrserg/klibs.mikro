package ru.astrainteractive.klibs.mikro.core.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class MapStateFlowExtTest {

    @Test
    fun GIVEN_state_flow_WHEN_mapStateFlow_THEN_value_is_transformed() {
        val source = MutableStateFlow(2)
        val mapped = source.mapStateFlow { value -> value * 10 }
        assertEquals(20, mapped.value)
    }

    @Test
    fun GIVEN_mapped_state_flow_WHEN_source_changes_THEN_value_reflects_latest() {
        val source = MutableStateFlow(2)
        val mapped = source.mapStateFlow { value -> value * 10 }
        source.value = 5
        assertEquals(50, mapped.value)
    }

    @Test
    fun GIVEN_two_states_WHEN_combineStates_THEN_value_combines_both() {
        val first = MutableStateFlow(3)
        val second = MutableStateFlow(4)
        val combined = combineStates(first, second) { left, right -> left + right }
        assertEquals(7, combined.value)
        first.value = 10
        assertEquals(14, combined.value)
    }

    @Test
    fun GIVEN_three_states_WHEN_combineStates_THEN_value_combines_all() {
        val first = MutableStateFlow(1)
        val second = MutableStateFlow(2)
        val third = MutableStateFlow(3)
        val combined = combineStates(first, second, third) { a, b, c -> a + b + c }
        assertEquals(6, combined.value)
        third.value = 10
        assertEquals(13, combined.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun GIVEN_mapped_state_flow_WHEN_collected_THEN_emits_distinct_transformed_values() = runTest {
        val source = MutableStateFlow(1)
        val mapped = source.mapStateFlow { value -> value * 2 }
        val collected = mutableListOf<Int>()

        val job = backgroundScope.launch {
            mapped.collect { value -> collected.add(value) }
        }
        runCurrent()

        source.value = 2
        runCurrent()
        source.value = 2
        runCurrent()
        source.value = 3
        runCurrent()
        job.cancel()

        assertEquals(listOf(2, 4, 6), collected)
    }
}
