package ru.astrainteractive.klibs.mikro.core.coroutines.util

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FlowExtTest {

    @Test
    fun GIVEN_flow_WHEN_to_cached_flow_THEN_transform_receives_previous_transformed_value() {
        runTest {
            val result = flowOf(
                1,
                2,
                3
            )
                .toCachedFlow<Int, Int> { value, previous -> value + (previous ?: 0) }
                .toList()
            // previous starts null (treated as 0), then carries the previously emitted result:
            // 1+0=1, 2+1=3, 3+3=6
            assertEquals(
                listOf(
                    1,
                    3,
                    6
                ),
                result
            )
        }
    }
}
