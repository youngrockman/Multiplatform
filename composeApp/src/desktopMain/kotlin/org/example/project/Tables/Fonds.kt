package org.example.project.Tables

import org.jetbrains.exposed.sql.Table

object Fonds : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name",  255)
    val balance = integer("balance")

    override val primaryKey = PrimaryKey(id)
}