package ru.astrainteractive.klibs.mikro.core.flow.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import ru.astrainteractive.klibs.mikro.core.flow.stateflow.DerivedStateFlow

public fun <T, R> StateFlow<T>.toStateFlow(transform: (T) -> R): StateFlow<R> {
    return DerivedStateFlow(
        getValue = { transform.invoke(this.value) },
        flow = this.map(transform)
    )
}

/**
 * Returns [StateFlow] from [flow] having initial value from calculation of [getValue]
 */
public fun <T> combineStates(
    getValue: () -> T,
    flow: Flow<T>
): StateFlow<T> {
    return DerivedStateFlow(getValue, flow)
}

/**
 * Combines all [stateFlows] and transforms them into another [StateFlow] with [transform]
 */
public inline fun <reified T, R> combineStates(
    vararg stateFlows: StateFlow<T>,
    crossinline transform: (Array<T>) -> R
): StateFlow<R> {
    return combineStates(
        getValue = { transform.invoke(stateFlows.map(StateFlow<T>::value).toTypedArray()) },
        flow = combine(*stateFlows, transform = transform)
    )
}

/**
 * Variant of [combineStates] for combining 2 state flows
 */
public inline fun <reified T1, reified T2, R> combineStates(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    crossinline transform: (T1, T2) -> R
): StateFlow<R> {
    return combineStates(flow1, flow2) { (t1, t2) ->
        transform.invoke(
            t1 as T1,
            t2 as T2,
        )
    }
}

/**
 * Variant of [combineStates] for combining 3 state flows
 */
public inline fun <reified T1, reified T2, reified T3, R> combineStates(
    flow1: StateFlow<T1>,
    flow2: StateFlow<T2>,
    flow3: StateFlow<T3>,
    crossinline transform: (T1, T2, T3) -> R
): StateFlow<R> {
    return combineStates(
        flow1,
        flow2,
        flow3
    ) { (t1, t2, t3) ->
        transform.invoke(
            t1 as T1,
            t2 as T2,
            t3 as T3
        )
    }
}
