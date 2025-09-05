package ru.astrainteractive.klibs.mikro.core.logging

import org.slf4j.LoggerFactory
import org.slf4j.Logger as SLF4JLogger

internal class Slf4jLogger(
    override val TAG: String,
    private val logger: SLF4JLogger = LoggerFactory.getLogger(TAG)
) : Logger {
    override fun error(logMessage: () -> String) {
        logger.error(logMessage.invoke())
    }

    override fun error(error: Throwable?, logMessage: () -> String) {
        logger.error(logMessage.invoke(), error)
    }

    override fun info(logMessage: () -> String) {
        logger.info(logMessage.invoke())
    }

    override fun verbose(logMessage: () -> String) {
        logger.trace(logMessage.invoke())
    }

    override fun warn(logMessage: () -> String) {
        logger.warn(logMessage.invoke())
    }

    override fun debug(logMessage: () -> String) {
        logger.debug(logMessage.invoke())
    }
}
