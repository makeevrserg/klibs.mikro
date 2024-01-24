package ru.astrainteractive.klibs.mikro.extensions.arkivanov

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

/**
 * This feature allows us to use [CoroutineScope] inside Components
 *
 * @see <a href="https://github.com/AJIEKCX/SpaceXRockets/blob/main/core/src/commonMain/kotlin/ru/alexpanov/core/feature/CoroutineFeature.kt">Source</href>
 *
 *
 * @author @AJIEKCX
 */
@Suppress("MaxLineLength")
interface CoroutineFeature : InstanceKeeper.Instance, CoroutineScope {
    override val coroutineContext: CoroutineContext
    override fun onDestroy() {
        cancel()
    }

    class Main : CoroutineFeature {
        override val coroutineContext = SupervisorJob() + Dispatchers.Main.immediate
    }

    class Unconfined : CoroutineFeature {
        override val coroutineContext = SupervisorJob() + Dispatchers.Unconfined
    }

    class Default(override val coroutineContext: CoroutineContext) : CoroutineFeature {
        constructor(coroutineDispatcher: CoroutineDispatcher) : this(
            coroutineContext = SupervisorJob() + coroutineDispatcher
        )
    }
}
