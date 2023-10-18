package ru.astrainteractive.klibs.mikro.extensions

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc

class JvmStringProvider : StringProvider {
    override fun toString(stringDesc: StringDesc): String {
        return stringDesc.localized()
    }

    override fun toString(stringResource: StringResource): String {
        return stringResource.localized()
    }
}
