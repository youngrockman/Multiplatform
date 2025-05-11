package org.example.project.Models

import java.time.LocalDate

data class User(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val surname: String,
    val gender: String,
    val photopath: String,
    val birthday: LocalDate,
    val country: String,
    val active: Boolean
)
