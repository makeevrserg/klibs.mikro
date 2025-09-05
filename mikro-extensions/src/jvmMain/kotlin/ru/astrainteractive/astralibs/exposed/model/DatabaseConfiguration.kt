package ru.astrainteractive.astralibs.exposed.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Represents a configuration for connecting to a database.
 *
 * This sealed interface defines the structure for various types of database configurations,
 * including MySQL, H2, and SQLite. Each implementation provides specific details required
 * to establish a connection to the respective database.
 */
@Serializable
sealed interface DatabaseConfiguration {

    /**
     * The JDBC driver class name for the database.
     */
    val driver: String

    /**
     * Additional arguments to be passed to the database connection URL.
     */
    val arguments: List<String>

    /**
     * A string representation of the arguments for the database connection URL.
     *
     * This property formats the list of arguments into a query string format.
     * If there are no arguments, it returns an empty string.
     */
    @Transient
    val stringArgument: String
        get() = if (arguments.isNotEmpty()) arguments.joinToString(separator = "&", prefix = "?") else ""

    /**
     * Configuration for connecting to a MySQL database.
     *
     * @property host The hostname or IP address of the MySQL server.
     * @property port The port number on which the MySQL server is running.
     * @property user The username for authenticating with the MySQL server.
     * @property password The password for authenticating with the MySQL server.
     * @property name The name of the database to connect to.
     * @property driver The JDBC driver class name for MySQL.
     * @property arguments Additional arguments to be passed to the database connection URL.
     */
    @SerialName("MySql")
    @Serializable
    @Suppress("LongParameterList")
    class MySql(
        val host: String,
        val port: Int,
        val user: String,
        val password: String,
        val name: String,
        override val driver: String = "com.mysql.cj.jdbc.Driver",
        override val arguments: List<String> = emptyList()
    ) : DatabaseConfiguration

    /**
     * Configuration for connecting to an H2 database.
     *
     * @property path The path of the H2 database file.
     * @property driver The JDBC driver class name for H2.
     * @property arguments Additional arguments to be passed to the database connection URL.
     */
    @SerialName("H2")
    @Serializable
    data class H2(
        val path: String,
        override val driver: String = "org.h2.Driver",
        override val arguments: List<String> = emptyList()
    ) : DatabaseConfiguration

    /**
     * Configuration for connecting to an SQLite database.
     *
     * @property path The path of the SQLite database file.
     * @property driver The JDBC driver class name for SQLite.
     * @property arguments Additional arguments to be passed to the database connection URL.
     */
    @SerialName("SQLite")
    @Serializable
    data class SQLite(
        val path: String,
        override val driver: String = "org.sqlite.JDBC",
        override val arguments: List<String> = emptyList()
    ) : DatabaseConfiguration
}
