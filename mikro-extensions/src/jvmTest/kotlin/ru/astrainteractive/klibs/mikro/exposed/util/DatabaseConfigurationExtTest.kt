package ru.astrainteractive.klibs.mikro.exposed.util

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
import ru.astrainteractive.klibs.mikro.exposed.model.DatabaseConfiguration
import kotlin.io.path.createTempFile
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DatabaseConfigurationExtTest {

    private val inMemoryConfiguration = DatabaseConfiguration.H2(path = "mem:connect-as-flow")

    @Test
    fun GIVEN_sqlite_configuration_WHEN_url_is_built_THEN_url_ends_with_path() {
        val configuration = DatabaseConfiguration.SQLite(path = "/data/bridge.db")

        assertEquals("jdbc:sqlite:/data/bridge.db", configuration.getUrl())
    }

    @Test
    fun GIVEN_sqlite_configuration_with_arguments_WHEN_url_is_built_THEN_arguments_follow_path() {
        val configuration = DatabaseConfiguration.SQLite(
            path = "/data/bridge.db",
            arguments = listOf("journal_mode=WAL", "busy_timeout=5000")
        )

        assertEquals("jdbc:sqlite:/data/bridge.db?journal_mode=WAL&busy_timeout=5000", configuration.getUrl())
    }

    @Test
    fun GIVEN_collection_is_active_WHEN_database_is_emitted_THEN_database_is_registered() = runTest {
        inMemoryConfiguration.connectAsFlow()
            .take(1)
            .collect { database ->
                assertEquals(database, TransactionManager.managerFor(database).db)
            }
    }

    @Test
    fun GIVEN_reachable_database_WHEN_collection_ends_THEN_database_is_unregistered() = runTest {
        val database = inMemoryConfiguration.connectAsFlow().first()

        assertFailsWith<IllegalStateException> { TransactionManager.managerFor(database) }
    }

    @Test
    fun GIVEN_database_folder_cannot_be_created_WHEN_collection_ends_THEN_database_is_unregistered() = runTest {
        val blockingFile = createTempFile()
        blockingFile.toFile().deleteOnExit()
        val configuration = DatabaseConfiguration.H2(path = blockingFile.resolve("database").toString())

        val database = configuration.connectAsFlow().first()

        assertFailsWith<IllegalStateException> { TransactionManager.managerFor(database) }
    }

    @Test
    fun GIVEN_collector_cancelled_before_emission_WHEN_producer_runs_THEN_no_database_stays_registered() = runTest {
        val primaryDatabase = TransactionManager.primaryDatabase

        coroutineScope {
            inMemoryConfiguration.connectAsFlow()
                .produceIn(this)
                .cancel()
        }

        assertEquals(primaryDatabase, TransactionManager.primaryDatabase)
    }
}
