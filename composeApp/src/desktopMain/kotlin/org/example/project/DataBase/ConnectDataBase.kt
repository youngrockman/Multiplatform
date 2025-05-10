package org.example.project.DataBase

import org.jetbrains.exposed.sql.Database

fun connectToDatabase() {
    Database.connect(
        "jdbc:postgresql://45.67.56.214:5421/user15?currentSchema=public2",
        user = "user15",
        password = "3XkvwMOb"
    )
}
