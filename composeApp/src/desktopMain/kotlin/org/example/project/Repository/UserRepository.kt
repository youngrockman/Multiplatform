package org.example.project.Repository

import org.example.project.Models.Fond
import org.example.project.Models.User
import org.example.project.Tables.Fonds
import org.example.project.Tables.Users
import org.example.project.DataBase.connectToDatabase
import org.jetbrains.exposed.sql.SqlExpressionBuilder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

actual class UserRepository {
    actual fun fetchUsers(): List<User> {
        connectToDatabase()

        return transaction {
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
    }
    actual fun regUser(user: User): Boolean{
        connectToDatabase()

        return try {
            transaction {
                Users.insert {
                    it[Users.id] = user.id
                    it[Users.email] = user.email
                    it[Users.password] = user.password
                    it[Users.name] = user.name
                    it[Users.surname] = user.surname
                    it[Users.gender] = user.gender
                    it[Users.photopath] = user.photopath
                    it[Users.birthday] = user.birthday
                    it[Users.country] = user.country
                }
            }
            true
        } catch (e: Exception) {
            println("Ошибка при регистрации: ${e.message}")
            false
        }
    }
    actual fun fetchFonds(): List<Fond>{
        connectToDatabase()

        return transaction {
            Fonds.selectAll().map {
                Fond(
                    id = it[Fonds.id],
                    name = it[Fonds.name],
                    balance = it[Fonds.balance]
                )
            }
        }
    }
    actual fun withdrawalBalance(idFond: Int, dedSum: Int): Boolean{
        connectToDatabase()

        return try {
            transaction {
                Fonds.update({ Fonds.id eq idFond }) {
                    with(SqlExpressionBuilder) {
                        it.update(Fonds.balance, Fonds.balance - dedSum)
                    }
                }
            }
            true
        } catch (e: Exception){
            println("Ошибка при снятии: ${e.message}")
            false
        }
    }
    actual fun getUserIdByEmail(email: String): Int {
        connectToDatabase()

        return transaction {
            Users.selectAll().where { Users.email eq email }.single()[Users.id]
        }
    }
    actual fun updateUserActive(idUser: Int): Boolean{
        connectToDatabase()

        return try {
            transaction {
                Users.update({ Users.id eq idUser }) {
                    it[Users.active] = true
                }
            }
            true
        } catch (e: Exception){
            println("Ошибка при обновлении: ${e.message}")
            false
        }
    }
    actual fun topUpBalance(idFond: Int, sum: Int): Boolean{
        connectToDatabase()

        return try {
            transaction {
                Fonds.update({ Fonds.id eq idFond }) {
                    with(SqlExpressionBuilder) {
                        it.update(Fonds.balance, Fonds.balance + sum)
                    }
                }
            }
            true
        } catch (e: Exception){
            println("Ошибка при пополнении: ${e.message}")
            false
        }
    }
}