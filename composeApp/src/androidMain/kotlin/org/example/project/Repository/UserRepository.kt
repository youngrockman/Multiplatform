package org.example.project.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import org.example.project.Models.Fond
import org.example.project.Models.User
import java.time.LocalDate

actual class UserRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    actual fun fetchUsers(): List<User> {
        var users = listOf(
            User(
                id = 1,
                email = "test@gmail.com",
                password = "123",
                name = "gg",
                surname = "ggwp",
                gender = "Мужской",
                photopath = "C:/DOCUMENT/kurumi.jpg",
                LocalDate.of(2006, 10, 10),
                country = "Russia",
                active = false
            ),
            User(
                id = 2,
                email = "test2@gmail.com",
                password = "1234",
                name = "lox",
                surname = "loxich",
                gender = "Мужской",
                photopath = "C:/DOCUMENT/kurumi.jpg",
                LocalDate.of(2007, 10, 10),
                country = "Russia",
                active = false
            ),        )
        return users
    }
    actual fun regUser(user: User): Boolean {
        return true
    }
    actual fun fetchFonds(): List<Fond> {
        var fonds = listOf(
            Fond(
                id = 1,
                name = "Spidvagon",
                balance = 1000
            ),
            Fond(
                id = 2,
                name = "IamAAAA",
                balance = 2000
            ),
            )
        return fonds
    }
    actual fun withdrawalBalance(idFond: Int, dedSum: Int): Boolean {
        return true
    }
    actual fun getUserIdByEmail(email: String): Int {
        return 0
    }
    actual fun updateUserActive(idUser: Int): Boolean{
        return true
    }
    actual fun topUpBalance(idFond: Int, sum: Int): Boolean{
        return true
    }
}