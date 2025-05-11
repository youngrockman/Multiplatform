package org.example.project.ViewModel

import org.example.project.Models.Fond
import org.example.project.Models.User
import org.example.project.Repository.UserRepository

class UserViewModel {
    private val userRepository = UserRepository()

    fun getUsers(): List<User> = userRepository.fetchUsers()

    fun regUser(user: User): Boolean = userRepository.regUser(user)

    fun getFond(): List<Fond> = userRepository.fetchFonds()

    fun withdrawalBalance(idFond: Int, dedSum: Int): Boolean =
        userRepository.withdrawalBalance(idFond, dedSum)

    fun getUserIdByEmail(email: String): Int =
        userRepository.getUserIdByEmail(email)

    fun updateUserActive(idUser: Int): Boolean =
        userRepository.updateUserActive(idUser)

    fun topUpBalance(idFond: Int, sum: Int): Boolean =
        userRepository.topUpBalance(idFond, sum)
}
