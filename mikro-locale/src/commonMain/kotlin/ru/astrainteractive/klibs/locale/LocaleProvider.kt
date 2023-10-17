package ru.astrainteractive.klibs.locale

/**
 * Locale provider is an interface which allows us to handle user locale without depending on JVM,Android or IOS
 *
 */
interface LocaleProvider {
    /**
     * List of all locales
     */
    val availableLocales: List<SharedLocale>

    /**
     * List of locales sorted alphabetically
     */
    val sortedLocaleNames: List<SharedLocale>

    /**
     * Returns default system language
     */
    val systemDefaultLanguage: SharedLocale

    /**
     * Converts locale from [SharedLocale.Name] into [SharedLocale]
     */
    fun fromName(name: SharedLocale.Name?): SharedLocale?

    /**
     * Converts locale from [SharedLocale.Code] into [SharedLocale]
     */
    fun fromCode(code: SharedLocale.Code?): SharedLocale?
}
