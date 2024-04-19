package ru.astrainteractive.klibs.mikro.extensions.arkivanov

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @see <a href="https://t.me/arkivanov_kt/9419">Source</href>
 *
 * @author @boozyyyy
 */
private class ValueStateFlow<out T : Any>(private val source: Value<T>) : StateFlow<T> {

    override val value: T
        get() = source.value

    override val replayCache: List<T>
        get() = listOf(source.value)

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        val flow = MutableStateFlow(source.value)
        val disposable = source.subscribe { flow.value = it }

        try {
            flow.collect(collector)
        } finally {
            disposable.cancel()
        }
    }
}

fun <T : Any> Value<T>.toStateFlow(): StateFlow<T> = ValueStateFlow(this)
