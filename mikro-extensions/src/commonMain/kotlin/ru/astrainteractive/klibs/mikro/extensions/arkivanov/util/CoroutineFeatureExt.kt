package ru.astrainteractive.klibs.mikro.extensions.arkivanov.util

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.cancel
import ru.astrainteractive.klibs.mikro.core.coroutines.api.CoroutineFeature
import ru.astrainteractive.klibs.mikro.extensions.arkivanov.api.EssentyCoroutineFeature

public fun CoroutineFeature.asEssentyCoroutineFeature(): EssentyCoroutineFeature {
    return object :
        EssentyCoroutineFeature,
        CoroutineFeature by this,
        InstanceKeeper.Instance {
        override fun onDestroy() {
            cancel()
        }
    }
}
