package com.axh

import org.h2.engine.Constants.DEFAULT_MAX_LENGTH_INPLACE_LOB
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertContentEquals

class ExposedH2Test {
    @Test
    fun `Reads & writes a small LOB`() {
        readWriteBlob(DEFAULT_MAX_LENGTH_INPLACE_LOB)
    }

    @Test
    fun `Reads & writes a large LOB`() {
        readWriteBlob(DEFAULT_MAX_LENGTH_INPLACE_LOB + 1)
    }

    private fun readWriteBlob(length: Int) {
        Database.connect( "jdbc:h2:mem:test")
        transaction {
            SchemaUtils.create(Values)

            val expected = Random.nextBytes(length)
            Values.insert { it[value] = ExposedBlob(expected) }

            val observed = Values.selectAll().map { it[Values.value].bytes }.single()
            assertContentEquals(expected, observed)
        }
    }

    object Values : LongIdTable() {
        val value = blob("value")
    }
}