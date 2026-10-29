@file:Suppress("KindShape")

package ru.astrainteractive.klibs.locale.provider

import ru.astrainteractive.klibs.locale.api.LocaleProvider
import ru.astrainteractive.klibs.locale.model.SharedLocale
import ru.astrainteractive.klibs.locale.util.toSharedLocale
import java.util.Locale

public object JVMLocaleProvider : LocaleProvider {
    override val systemDefaultLanguage: SharedLocale
        get() = Locale.getDefault().toSharedLocale()

    override val availableLocales: List<SharedLocale>
        get() = Locale.getAvailableLocales()
            .map(Locale::toSharedLocale)
            .distinctBy { locale -> locale.code.value }
            .distinctBy { locale -> locale.name.value }
            .filter { locale -> locale.name.value.isNotBlank() && locale.code.value.isNotBlank() }

    override val sortedLocaleNames: List<SharedLocale>
        get() = availableLocales
            .distinctBy(SharedLocale::code)
            .sortedBy { locale -> locale.name.value }

    override fun fromName(name: SharedLocale.Name?): SharedLocale? {
        name ?: return null
        return availableLocales
            .firstOrNull { locale -> locale.name.value.equals(name.value, true) }
    }

    override fun fromCode(code: SharedLocale.Code?): SharedLocale? {
        code ?: return null
        return if (code.value.contains("_")) {
            val (code, country) = code.value.split("_")
            val locale = Locale(code, country)
            return locale.toSharedLocale()
        } else {
            availableLocales
                .firstOrNull { locale -> locale.code.value.equals(code.value, true) }
        }
    }
}
