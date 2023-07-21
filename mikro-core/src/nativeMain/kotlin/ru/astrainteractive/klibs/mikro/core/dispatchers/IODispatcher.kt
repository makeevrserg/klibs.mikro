package ru.astrainteractive.klibs.mikro.core.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal actual object IODispatcher {
    actual val IO: CoroutineDispatcher = Dispatchers.IO
}
