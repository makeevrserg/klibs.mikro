package ru.astrainteractive.klibs.mikro.extensions

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

interface StringProvider {
    fun toString(stringDesc: StringDesc): String
    fun toString(stringResource: StringResource): String
}
