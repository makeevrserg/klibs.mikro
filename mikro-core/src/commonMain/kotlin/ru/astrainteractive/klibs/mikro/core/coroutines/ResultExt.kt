package ru.astrainteractive.klibs.mikro.core.coroutines

import kotlinx.coroutines.CancellationException

/**
 * Rethrows the failure if this [Result] contains a [CancellationException].
 *
 * This is useful when working with [Result], because catching exceptions into a
 * [Result] can accidentally swallow coroutine cancellation. Cancellation should
 * normally be propagated immediately instead of being handled as an ordinary failure.
 *
 * Returns this [Result] unchanged if it is successful or if its failure is not a
 * [CancellationException].
 *
 * @throws CancellationException if this [Result] failed with one.
 */
private fun <T> Result<T>.propagateCancellationException(): Result<T> {
    val t = exceptionOrNull() ?: return this
    if (t !is CancellationException) return this
    throw t
}
