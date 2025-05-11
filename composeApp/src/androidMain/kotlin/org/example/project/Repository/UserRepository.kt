package org.example.project.Repository

import android.os.Build
import androidx.annotation.RequiresApi
import org.example.project.Models.Fond
import org.example.project.Models.User
import java.time.LocalDate

actual class UserRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    actual fun fetchUsers(): List<User> {
        return listOf(
            createUser(
                id = 1,
                email = "test@gmail.com",
                password = "123",
                name = "gg",
                surname = "ggwp",
                gender = "Мужской",
                birthDate = LocalDate.of(2006, 10, 10),
                country = "Russia"
            ),
            createUser(
                id = 2,
                email = "test2@gmail.com",
                password = "1234",
                name = "lox",
                surname = "loxich",
                gender = "Мужской",
                birthDate = LocalDate.of(2007, 10, 10),
                country = "Russia"
            )
        )
    }

    actual fun regUser(user: User): Boolean = true

    actual fun fetchFonds(): List<Fond> {
        return listOf(
            createFond(id = 1, name = "Spidvagon", balance = 1000), //Кто понял тот понял
            createFond(id = 2, name = "IamAAAA", balance = 2000)
        )
    }

    actual fun withdrawalBalance(idFond: Int, dedSum: Int): Boolean = true

    actual fun getUserIdByEmail(email: String): Int = 0

    actual fun updateUserActive(idUser: Int): Boolean = true

    actual fun topUpBalance(idFond: Int, sum: Int): Boolean = true

    private fun createUser(id: Int, email: String, password: String, name: String, surname: String, gender: String, birthDate: LocalDate, country: String) =
        User(
            id = id,
            email = email,
            password = password,
            name = name,
            surname = surname,
            gender = gender,
            photopath = "C:/DOCUMENT/kurumi.jpg",
            birthday = birthDate,
            country = country,
            active = false,
        )

    private fun createFond(id: Int, name: String, balance: Int) =
        Fond(
            id = id,
            name = name,
            balance = balance
        )
}
