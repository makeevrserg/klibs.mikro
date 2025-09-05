package ru.astrainteractive.astralibs.exposed.util

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.DatabaseConfig
import ru.astrainteractive.astralibs.exposed.model.DatabaseConfiguration
import kotlin.time.Duration.Companion.seconds

/**
 * Generates the JDBC URL for connecting to the database.
 *
 * @return The JDBC URL string for the database connection.
 */
fun DatabaseConfiguration.getUrl(): String {
    return when (this) {
        is DatabaseConfiguration.H2 -> "jdbc:h2:${path}$stringArgument"
        is DatabaseConfiguration.SQLite -> "jdbc:sqlite:$path}$stringArgument"
        is DatabaseConfiguration.MySql -> "jdbc:mysql://$host:$port/${name}$stringArgument"
    }
}

/**
 * Establishes a connection to the database using the provided configuration.
 *
 * @param databaseConfig Optional configuration for the database connection.
 * @return A [Database] object representing the established connection.
 */
fun DatabaseConfiguration.connect(
    databaseConfig: DatabaseConfig? = DatabaseConfig {
        defaultMinRetryDelay = 5.seconds.inWholeMilliseconds
        defaultMaxRetryDelay = 30.seconds.inWholeMilliseconds
    },
): Database {
    return when (this) {
        is DatabaseConfiguration.H2 -> Database.connect(
            url = getUrl(),
            driver = driver,
            databaseConfig = databaseConfig,
        )

        is DatabaseConfiguration.MySql -> Database.connect(
            url = getUrl(),
            driver = driver,
            user = user,
            password = password,
            databaseConfig = databaseConfig,
        )

        is DatabaseConfiguration.SQLite -> Database.connect(
            url = getUrl(),
            driver = driver,
            databaseConfig = databaseConfig,
        )
    }
}
