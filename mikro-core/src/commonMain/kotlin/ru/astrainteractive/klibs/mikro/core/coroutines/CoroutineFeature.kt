package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.astrainteractive.klibs.mikro.core.dispatchers.DefaultKotlinDispatchers
import kotlin.coroutines.CoroutineContext

interface CoroutineFeature : CoroutineScope {
    override val coroutineContext: CoroutineContext

    class Default(override val coroutineContext: CoroutineContext) : CoroutineFeature {
        constructor(coroutineDispatcher: CoroutineDispatcher) : this(
            coroutineContext = SupervisorJob()
                .plus(coroutineDispatcher)
        )
    }

    companion object {
        val Default: CoroutineFeature
            get() = CoroutineFeature.Default(Dispatchers.Default)
        val Main: CoroutineFeature
            get() = CoroutineFeature.Default(Dispatchers.Main)
        val Unconfined: CoroutineFeature
            get() = CoroutineFeature.Default(Dispatchers.Unconfined)
        val IO: CoroutineFeature
            get() = CoroutineFeature.Default(DefaultKotlinDispatchers.IO)
    }
}
