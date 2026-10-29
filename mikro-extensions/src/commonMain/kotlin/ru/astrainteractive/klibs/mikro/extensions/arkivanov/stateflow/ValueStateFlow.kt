@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.extensions.arkivanov.stateflow

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @see [Source](https://t.me/arkivanov_kt/9419)
 *
 * @author @boozyyyy
 */
internal class ValueStateFlow<out T : Any>(private val source: Value<T>) : StateFlow<T> {

    override val value: T
        get() = source.value

    override val replayCache: List<T>
        get() = listOf(source.value)

    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        val flow = MutableStateFlow(source.value)
        val disposable = source.subscribe { value -> flow.value = value }

        try {
            flow.collect(collector)
        } finally {
            disposable.cancel()
        }
    }
}
