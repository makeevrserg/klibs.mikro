package ru.astrainteractive.klibs.mikro.exposed.util

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.jetbrains.exposed.v1.core.DatabaseConfig
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
import ru.astrainteractive.klibs.mikro.exposed.model.DatabaseConfiguration
import kotlin.time.Duration.Companion.seconds

/**
 * Generates the JDBC URL for connecting to the database.
 *
 * @return The JDBC URL string for the database connection.
 */
public fun DatabaseConfiguration.toJdbcUrl(): String {
    return when (this) {
        is DatabaseConfiguration.H2 -> "jdbc:h2:${path}$stringArgument"
        is DatabaseConfiguration.SQLite -> "jdbc:sqlite:${path}$stringArgument"
        is DatabaseConfiguration.MySql -> "jdbc:mysql://$host:$port/${name}$stringArgument"
        is DatabaseConfiguration.MariaDB -> "jdbc:mariadb://$host:$port/${name}$stringArgument"
    }
}

/**
 * Establishes a connection to the database using the provided configuration.
 *
 * @param databaseConfig Optional configuration for the database connection.
 * @return A [Database] object representing the established connection.
 */
public fun DatabaseConfiguration.connect(
    databaseConfig: DatabaseConfig? = DatabaseConfig {
        defaultMinRetryDelay = 5.seconds.inWholeMilliseconds
        defaultMaxRetryDelay = 30.seconds.inWholeMilliseconds
    },
): Database {
    return when (this) {
        is DatabaseConfiguration.H2 -> Database.connect(
            url = toJdbcUrl(),
            driver = driver,
            databaseConfig = databaseConfig,
        )

        is DatabaseConfiguration.MySql -> Database.connect(
            url = toJdbcUrl(),
            driver = driver,
            user = user,
            password = password,
            databaseConfig = databaseConfig,
        )

        is DatabaseConfiguration.SQLite -> Database.connect(
            url = toJdbcUrl(),
            driver = driver,
            databaseConfig = databaseConfig,
        )

        is DatabaseConfiguration.MariaDB -> Database.connect(
            url = toJdbcUrl(),
            driver = driver,
            user = user,
            password = password,
            databaseConfig = databaseConfig,
        )
    }
}

/**
 * Establishes a connection to the database and exposes it as a [callbackFlow].
 *
 * The connection is created when the flow is collected and automatically closed
 * when the flow collection is canceled. When the collection ends, the database
 * is unregistered from the [TransactionManager].
 *
 * @param databaseConfig Optional configuration for the database connection.
 * @return A [callbackFlow] emitting the [Database] object representing the established connection.
 */
public fun DatabaseConfiguration.connectAsFlow(
    databaseConfig: DatabaseConfig? = DatabaseConfig {
        defaultMinRetryDelay = 5.seconds.inWholeMilliseconds
        defaultMaxRetryDelay = 30.seconds.inWholeMilliseconds
    }
): Flow<Database> {
    return callbackFlow {
        val configuration = this@connectAsFlow
        val database = configuration.connect(databaseConfig)
        try {
            send(database)
            awaitClose()
        } finally {
            TransactionManager.closeAndUnregister(database)
        }
    }
}
