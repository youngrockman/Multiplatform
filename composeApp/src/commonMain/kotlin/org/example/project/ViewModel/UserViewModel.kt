package org.example.project.ViewModel

import org.example.project.Models.Fond
import org.example.project.Models.User
import org.example.project.Repository.UserRepository

class UserViewModel {
    private val userRepository = UserRepository()

    fun getUsers(): List<User> {
        return userRepository.fetchUsers()
    }

    fun regUser(user: User): Boolean {
        return userRepository.regUser(user)
    }

    fun getFond(): List<Fond>{
        return userRepository.fetchFonds()
    }

    fun withdrawalBalance(idFond: Int, dedSum: Int): Boolean{
        return userRepository.withdrawalBalance(idFond, dedSum)
    }

    fun getUserIdByEmail(email: String): Int{
        return userRepository.getUserIdByEmail(email)
    }

    fun updateUserActive(idUser: Int): Boolean{
        return userRepository.updateUserActive(idUser)
    }

    fun topUpBalance(idFond: Int, sum: Int): Boolean{
        return userRepository.topUpBalance(idFond, sum)
    }
}