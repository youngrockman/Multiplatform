package org.example.project.Repository

import org.example.project.Models.Fond
import org.example.project.Models.User

expect class UserRepository() {
    fun fetchUsers(): List<User>
    fun regUser(user: User): Boolean
    fun fetchFonds(): List<Fond>
    fun withdrawalBalance(idFond: Int, dedSum: Int): Boolean
    fun getUserIdByEmail(email: String): Int
    fun updateUserActive(idUser: Int): Boolean
    fun topUpBalance(idFond: Int, sum: Int): Boolean
}
