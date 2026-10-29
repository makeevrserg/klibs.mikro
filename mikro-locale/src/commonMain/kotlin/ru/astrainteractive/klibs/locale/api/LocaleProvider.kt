package ru.astrainteractive.klibs.locale.api

import ru.astrainteractive.klibs.locale.model.SharedLocale

/**
 * Locale provider is an interface which allows us to handle user locale without depending on JVM,Android or IOS
 *
 */
public interface LocaleProvider {
    /**
     * List of all locales
     */
    public val availableLocales: List<SharedLocale>

    /**
     * List of locales sorted alphabetically
     */
    public val sortedLocaleNames: List<SharedLocale>

    /**
     * Returns default system language
     */
    public val systemDefaultLanguage: SharedLocale

    /**
     * Converts locale from [SharedLocale.Name] into [SharedLocale]
     */
    public fun fromName(name: SharedLocale.Name?): SharedLocale?

    /**
     * Converts locale from [SharedLocale.Code] into [SharedLocale]
     */
    public fun fromCode(code: SharedLocale.Code?): SharedLocale?
}
