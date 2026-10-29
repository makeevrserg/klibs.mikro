@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.extensions.stringprovider.provider

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import ru.astrainteractive.klibs.mikro.extensions.stringprovider.api.StringProvider

public class JvmStringProvider : StringProvider {
    override fun toString(stringDesc: StringDesc): String {
        return stringDesc.localized()
    }

    override fun toString(stringResource: StringResource): String {
        return stringResource.localized()
    }
}
