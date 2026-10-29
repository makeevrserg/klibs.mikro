@file:Suppress("KindShape")

package ru.astrainteractive.klibs.locale.provider

import platform.Foundation.NSLocale
import platform.Foundation.NSLocaleIdentifier
import platform.Foundation.availableLocaleIdentifiers
import platform.Foundation.currentLocale
import platform.Foundation.localeIdentifier
import ru.astrainteractive.klibs.locale.api.LocaleProvider
import ru.astrainteractive.klibs.locale.model.SharedLocale

public object AppleLocaleProvider : LocaleProvider {

    override val availableLocales: List<SharedLocale>
        get() = NSLocale.availableLocaleIdentifiers
            .asSequence()
            .mapNotNull { identifier -> identifier as? String? }
            .map(SharedLocale::Code)
            .mapNotNull(AppleLocaleProvider::fromCode)
            .distinctBy { locale -> locale.code.value }
            .distinctBy { locale -> locale.name.value }
            .toList()

    override val sortedLocaleNames: List<SharedLocale>
        get() = availableLocales
            .distinctBy(SharedLocale::code)
            .sortedBy { locale -> locale.name.value }

    override val systemDefaultLanguage: SharedLocale
        get() = fromCode(SharedLocale.Code(NSLocale.currentLocale.localeIdentifier))!!

    override fun fromName(name: SharedLocale.Name?): SharedLocale? {
        name ?: return null
        return availableLocales
            .firstOrNull { locale -> locale.name.value.equals(name.value, true) }
    }

    override fun fromCode(code: SharedLocale.Code?): SharedLocale? {
        val code = code?.value ?: return null
        val locale = NSLocale(localeIdentifier = code)
        val name = locale.displayNameForKey(NSLocaleIdentifier, code) ?: error("NSLocaleIdentifier error")
        return SharedLocale(
            code = SharedLocale.Code(code),
            name = SharedLocale.Name(name)
        )
    }
}
