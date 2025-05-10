package org.example.project.ViewModel

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Locale

class TimerViewModel : ViewModel() {

    init {
        startTimer()
    }

    private val _remainingTime = MutableStateFlow("18 дней 17 часов и 10 минут до старта марафона")
    val remainingTime: StateFlow<String> = _remainingTime

    private fun startTimer() {
        viewModelScope.launch {
            var remainingMillis = (18 * 24 * 60 * 60 * 1000) + (17 * 60 * 60 * 1000) + (10 * 60 * 1000)

            while (remainingMillis > 0) {
                delay(1000)
                remainingMillis -= 1000

                val days = (remainingMillis / (1000 * 60 * 60 * 24)).toInt()
                val hours = (remainingMillis % (1000 * 60 * 60 * 24) / (1000 * 60 * 60)).toInt()
                val minutes = (remainingMillis % (1000 * 60 * 60) / (1000 * 60)).toInt()

                _remainingTime.value = "$days дней $hours часов и $minutes минут до старта марафона!"
            }

            _remainingTime.value = "Марафон начался!"
        }
    }
}