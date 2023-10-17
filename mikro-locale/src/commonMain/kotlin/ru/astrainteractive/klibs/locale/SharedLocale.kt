package ru.astrainteractive.klibs.locale

import kotlin.jvm.JvmInline

/**
 * Shared locale is a wrapper to handle locale in KMM way
 */
data class SharedLocale(
    val code: Code,
    val name: Name
) {
    /**
     * [Code] is a value class to store locale code: ru_RU or en_US
     */
    @JvmInline
    value class Code(val value: String)

    /**
     * [Name] is a value class to store locale translated name: Russian, English
     */
    @JvmInline
    value class Name(val value: String)
    companion object {
        /**
         * If server has null in locale, or it can't be parsed [SharedLocale.Empty] will be used
         */
        val Empty: SharedLocale
            get() = SharedLocale(Code(""), Name(""))
    }
}
