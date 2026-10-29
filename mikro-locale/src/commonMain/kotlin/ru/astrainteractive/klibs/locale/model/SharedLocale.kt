package ru.astrainteractive.klibs.locale.model

import kotlin.jvm.JvmInline

/**
 * Shared locale is a wrapper to handle locale in KMM way
 */
public data class SharedLocale(
    public val code: Code,
    public val name: Name
) {
    /**
     * [Code] is a value class to store locale code: ru_RU or en_US
     */
    @JvmInline
    public value class Code(public val value: String)

    /**
     * [Name] is a value class to store locale translated name: Russian, English
     */
    @JvmInline
    public value class Name(public val value: String)
    public companion object {
        /**
         * If server has null in locale, or it can't be parsed [SharedLocale.Empty] will be used
         */
        public val Empty: SharedLocale
            get() = SharedLocale(Code(""), Name(""))
    }
}
