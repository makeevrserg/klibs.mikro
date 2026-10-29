@file:Suppress("KindShape")

package ru.astrainteractive.klibs.mikro.extensions.stringprovider.provider

import android.content.Context
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.desc.StringDesc
import ru.astrainteractive.klibs.mikro.extensions.stringprovider.api.StringProvider

public class AndroidStringProvider(private val context: Context) : StringProvider {
    override fun toString(stringDesc: StringDesc): String {
        return stringDesc.toString(context)
    }

    override fun toString(stringResource: StringResource): String {
        return stringResource.getString(context)
    }
}
