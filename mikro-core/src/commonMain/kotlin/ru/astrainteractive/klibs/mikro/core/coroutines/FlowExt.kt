package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

public fun <T> Flow<T>.onLatest(
    action: suspend (T) -> Unit
): Flow<T> = transformLatest { value ->
    action(value)
    return@transformLatest emit(value)
}

inline fun <T, R> Flow<T>.mapCached(
    crossinline transform: suspend (value: T, previous: R?) -> R
): Flow<R> = flow {
    var latest: R? = null
    this@mapCached.collect {
        val current = transform.invoke(it, latest)
        latest = current
        emit(current)
    }
}

/**
 * Transforms a [Flow] by applying a suspend [transform] function that has access to the previously emitted value.
 *
 * The transformation result is cached internally and the transformed flow is shared as a [SharedFlow]
 * using the given [scope], [started] strategy, and [replay] size.
 *
 * This function is useful when the transformation depends on the previously emitted result.
 *
 * @param scope The [CoroutineScope] in which the shared flow is active.
 * @param started The [SharingStarted] strategy to control when sharing starts/stops.
 * @param dispatcher The [Dispatchers] on which flow will be running
 * @param replay The number of values replayed to new subscribers.
 * @param transform A suspend lambda that takes the current upstream value and the previous transformed value.
 * @return A [SharedFlow] of transformed values.
 */
inline fun <T, R> Flow<T>.mapCached(
    scope: CoroutineScope,
    started: SharingStarted = SharingStarted.Eagerly,
    dispatcher: CoroutineContext = Dispatchers.Unconfined,
    replay: Int = 1,
    crossinline transform: suspend (value: T, previous: R?) -> R
): SharedFlow<R> = mapCached(transform)
    .flowOn(dispatcher)
    .shareIn(scope, started, replay)

fun <T> Flow<T>?.orEmpty(): Flow<T> = this ?: emptyFlow()

fun <T> Flow<T>?.orNullable(): Flow<T?> = this ?: flowOf(null)

fun <T> Flow<T>.merge(flow: Flow<T>): Flow<T> = listOf(this, flow).merge()

/**
 * Emits the first value from the flow, applies the given transform
 * and ignores next values while the transform is still executing
 *
 * This is similar to collectLatest, but it doesn't end job when new
 * value is emitted
 */
fun <T, K> Flow<T>.throttleLatest(
    transform: suspend (T) -> K
): Flow<K> = channelFlow {
    val scope = this
    var job: Job? = null

    collectLatest { value ->
        job?.join()
        job = scope.launch {
            send(transform(value))
        }
    }
}
