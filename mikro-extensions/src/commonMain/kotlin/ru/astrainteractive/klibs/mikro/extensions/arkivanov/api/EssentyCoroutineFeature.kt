package ru.astrainteractive.klibs.mikro.extensions.arkivanov.api

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import ru.astrainteractive.klibs.mikro.core.coroutines.api.CoroutineFeature

public interface EssentyCoroutineFeature : CoroutineFeature, InstanceKeeper.Instance
