@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.core.dispatchers.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import ru.astrainteractive.klibs.mikro.core.dispatchers.api.IODispatcher
import ru.astrainteractive.klibs.mikro.core.dispatchers.api.KotlinDispatchers

public object DefaultKotlinDispatchers : KotlinDispatchers {
    override val Main: MainCoroutineDispatcher
        get() = Dispatchers.Main
    override val IO: CoroutineDispatcher
        get() = IODispatcher.IO
    override val Default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val Unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
