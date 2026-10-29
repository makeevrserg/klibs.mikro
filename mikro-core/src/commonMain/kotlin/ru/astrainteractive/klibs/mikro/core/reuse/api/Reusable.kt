package ru.astrainteractive.klibs.mikro.core.reuse.api

public interface Reusable<T : Any> {
    public val value: T

    public val orNull: T?

    public val isReferenced: Boolean
}
