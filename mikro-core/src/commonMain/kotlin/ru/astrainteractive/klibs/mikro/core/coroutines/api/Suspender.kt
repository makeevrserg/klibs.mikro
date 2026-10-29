package ru.astrainteractive.klibs.mikro.core.coroutines.api

/**
 * A utility class that manages a simple suspension mechanism based on a duration.
 *
 * Once [setSuspended] is called, the suspender enters a suspended state for the
 * specified [duration]. During this period, [isSuspended] will return `true`.
 * After the duration has elapsed, [isSuspended] will return `false`.
 *
 * Example usage:
 * ```
 * val suspender = Suspender(Duration.seconds(30))
 * suspender.setSuspended()
 * if (suspender.isSuspended()) {
 *     println("Still suspended.")
 * }
 * ```
 *
 * @property duration The duration for which the object remains suspended after being triggered.
 */
public interface Suspender {
    /**
     * Checks whether the current time is still within the suspension period.
     *
     * @return `true` if the suspender is still within the active suspension window, `false` otherwise.
     */
    public fun isSuspended(): Boolean

    /**
     * Marks the current moment as the start of the suspension period.
     */
    public fun setSuspended()
}
