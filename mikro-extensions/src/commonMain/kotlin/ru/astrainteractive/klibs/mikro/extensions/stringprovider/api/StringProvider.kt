package ru.astrainteractive.klibs.mikro.extensions.stringprovider.api

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

public interface StringProvider {
    public fun toString(stringDesc: StringDesc): String
    public fun toString(stringResource: StringResource): String
}
