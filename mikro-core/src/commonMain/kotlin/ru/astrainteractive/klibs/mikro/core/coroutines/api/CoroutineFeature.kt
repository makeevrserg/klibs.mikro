package ru.astrainteractive.klibs.mikro.core.coroutines.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import ru.astrainteractive.klibs.mikro.core.dispatchers.dispatcher.DefaultKotlinDispatchers
import kotlin.coroutines.CoroutineContext

public interface CoroutineFeature : CoroutineScope {
    override val coroutineContext: CoroutineContext

    public class Default(override val coroutineContext: CoroutineContext) : CoroutineFeature {
        public constructor(coroutineDispatcher: CoroutineDispatcher) : this(
            coroutineContext = SupervisorJob()
                .plus(coroutineDispatcher)
        )
    }

    public companion object {
        public val Default: CoroutineFeature
            get() = CoroutineFeature.Default(Dispatchers.Default)
        public val Main: CoroutineFeature
            get() = CoroutineFeature.Default(Dispatchers.Main)
        public val Unconfined: CoroutineFeature
            get() = CoroutineFeature.Default(Dispatchers.Unconfined)
        public val IO: CoroutineFeature
            get() = CoroutineFeature.Default(DefaultKotlinDispatchers.IO)
    }
}
