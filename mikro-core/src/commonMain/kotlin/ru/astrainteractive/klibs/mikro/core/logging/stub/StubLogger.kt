package ru.astrainteractive.klibs.mikro.core.logging.stub

import ru.astrainteractive.klibs.mikro.core.logging.api.Logger

/**
 * A no-op implementation of [Logger] that ignores all log messages.
 *
 * This is useful for testing, default fallbacks, or disabling logging entirely
 * without null checks or conditional logging.
 */
public object StubLogger : Logger {
    override val TAG: String = "StubLogger"

    override fun error(logMessage: () -> String): Unit = Unit

    override fun error(error: Throwable?, logMessage: () -> String): Unit = Unit

    override fun info(logMessage: () -> String): Unit = Unit

    override fun verbose(logMessage: () -> String): Unit = Unit

    override fun warn(logMessage: () -> String): Unit = Unit

    override fun debug(logMessage: () -> String): Unit = Unit
}
