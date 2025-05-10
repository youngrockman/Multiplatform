package org.example.project.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegRunnerViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var secondPassword by mutableStateOf("")
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var gender by mutableStateOf("")
    var country by mutableStateOf("")
    var photoPath by mutableStateOf("")
    var dateBirthday by mutableStateOf("")
}