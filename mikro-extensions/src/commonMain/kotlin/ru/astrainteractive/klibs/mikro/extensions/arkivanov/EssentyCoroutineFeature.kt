package ru.astrainteractive.klibs.mikro.extensions.arkivanov

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.cancel
import ru.astrainteractive.klibs.mikro.core.coroutines.CoroutineFeature

interface EssentyCoroutineFeature : CoroutineFeature, InstanceKeeper.Instance

fun CoroutineFeature.asEssentyCoroutineFeature(): EssentyCoroutineFeature {
    return object :
        EssentyCoroutineFeature,
        CoroutineFeature by this,
        InstanceKeeper.Instance {
        override fun onDestroy() {
            cancel()
        }
    }
}
