package ru.astrainteractive.klibs.mikro.core.dispatchers.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

/**
 * You can pass dispatchers also as interface in your DI
 */
@Suppress("VariableNaming")
public interface KotlinDispatchers {
    public val Main: MainCoroutineDispatcher
    public val IO: CoroutineDispatcher
    public val Default: CoroutineDispatcher
    public val Unconfined: CoroutineDispatcher
}
