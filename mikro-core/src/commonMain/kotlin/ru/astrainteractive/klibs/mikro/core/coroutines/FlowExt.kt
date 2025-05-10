package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transformLatest

public fun <T> Flow<T>.onLatest(
    action: suspend (T) -> Unit
): Flow<T> = transformLatest { value ->
    action(value)
    return@transformLatest emit(value)
}
