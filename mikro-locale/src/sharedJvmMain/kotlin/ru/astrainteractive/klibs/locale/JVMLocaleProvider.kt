package ru.astrainteractive.klibs.locale

import java.util.Locale

object JVMLocaleProvider : LocaleProvider {
    private fun String.capitalized(): String {
        return replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }
    }

    private val Locale.wrapper: SharedLocale
        get() = SharedLocale(
            code = SharedLocale.Code(language),
            name = SharedLocale.Name(displayLanguage.capitalized())
        )

    override val systemDefaultLanguage: SharedLocale
        get() = Locale.getDefault().wrapper

    override val availableLocales: List<SharedLocale>
        get() = Locale.getAvailableLocales()
            .map { it.wrapper }
            .distinctBy { it.code.value }
            .distinctBy { it.name.value }
            .filter { it.name.value.isNotBlank() && it.code.value.isNotBlank() }

    override val sortedLocaleNames: List<SharedLocale>
        get() = availableLocales
            .distinctBy { it.code }
            .sortedBy { it.name.value }

    override fun fromName(name: SharedLocale.Name?): SharedLocale? {
        name ?: return null
        return availableLocales
            .firstOrNull { it.name.value.equals(name.value, true) }
    }

    override fun fromCode(code: SharedLocale.Code?): SharedLocale? {
        code ?: return null
        return if (code.value.contains("_")) {
            val (code, country) = code.value.split("_")
            val locale = Locale(code, country)
            return locale.wrapper
        } else {
            availableLocales
                .firstOrNull { it.code.value.equals(code.value, true) }
        }
    }
}
