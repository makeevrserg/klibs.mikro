@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.extensions.stringprovider.provider

import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import dev.icerock.moko.resources.desc.desc
import ru.astrainteractive.klibs.mikro.extensions.stringprovider.api.StringProvider

public class AppleStringProvider : StringProvider {
    override fun toString(stringDesc: StringDesc): String {
        return stringDesc.localized()
    }

    override fun toString(stringResource: StringResource): String {
        return stringResource.desc().localized()
    }
}
