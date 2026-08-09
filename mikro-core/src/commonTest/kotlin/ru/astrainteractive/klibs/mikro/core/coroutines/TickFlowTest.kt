package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

class TickFlowTest {

    @Test
    fun GIVEN_tick_flow_WHEN_collected_THEN_keeps_emitting_until_cancelled() = runTest {
        val ticks = TickFlow(duration = 1.seconds)
            .take(3)
            .toList()
        assertEquals(listOf(Unit, Unit, Unit), ticks)
    }
}
