package org.example.project.Repository

import org.example.project.Models.Fond
import org.example.project.Models.User
import org.example.project.Tables.Fonds
import org.example.project.Tables.Users
import org.example.project.DataBase.connectToDatabase
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

actual class UserRepository {
    init {
        connectToDatabase()
    }

    actual fun fetchUsers(): List<User> = transaction {
        Users.selectAll().map {
            User(
                id = it[Users.id],
                email = it[Users.email],
                password = it[Users.password],
                name = it[Users.name],
                surname = it[Users.surname],
                gender = it[Users.gender],
                photopath = it[Users.photopath],
                birthday = it[Users.birthday],
                country = it[Users.country],
                active = it[Users.active]
            )
        }
    }

    actual fun regUser(user: User): Boolean = try {
        transaction {
            Users.insert {
                it[email] = user.email
                it[password] = user.password
                it[name] = user.name
                it[surname] = user.surname
                it[gender] = user.gender
                it[photopath] = user.photopath
                it[birthday] = user.birthday
                it[country] = user.country
                it[active] = user.active
            }
        }
        true
    } catch (e: Exception) {
        println("Ошибка при регистрации: ${e.message}")
        e.printStackTrace()
        false
    }

    actual fun fetchFonds(): List<Fond> = transaction {
        Fonds.selectAll().map {
            Fond(
                id = it[Fonds.id],
                name = it[Fonds.name],
                balance = it[Fonds.balance]
            )
        }
    }

    private fun updateFondBalance(idFond: Int, delta: Int): Boolean = try {
        transaction {
            Fonds.update({ Fonds.id eq idFond }) {
                with(SqlExpressionBuilder) {
                    it.update(Fonds.balance, Fonds.balance + delta)
                }
            }
        }
        true
    } catch (e: Exception) {
        println("Ошибка при изменении баланса: ${e.message}")
        e.printStackTrace()
        false
    }

    actual fun withdrawalBalance(idFond: Int, dedSum: Int): Boolean =
        updateFondBalance(idFond, -dedSum)

    actual fun topUpBalance(idFond: Int, sum: Int): Boolean =
        updateFondBalance(idFond, sum)

    actual fun getUserIdByEmail(email: String): Int {
        connectToDatabase()

        return transaction {
            Users.selectAll().where { Users.email eq email }.single()[Users.id]
        }
    }

    actual fun updateUserActive(idUser: Int): Boolean = try {
        transaction {
            Users.update({ Users.id eq idUser }) {
                it[active] = true
            }
        }
        true
    } catch (e: Exception) {
        println("Ошибка при обновлении активности: ${e.message}")
        e.printStackTrace()
        false
    }
}
