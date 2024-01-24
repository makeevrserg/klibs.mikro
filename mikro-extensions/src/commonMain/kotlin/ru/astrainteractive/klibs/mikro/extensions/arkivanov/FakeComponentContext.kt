package ru.astrainteractive.klibs.mikro.extensions.arkivanov

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

/**
 * Creates custom [ComponentContext] which is useful for previews
 */
class FakeComponentContext : ComponentContext by DefaultComponentContext(LifecycleRegistry())
