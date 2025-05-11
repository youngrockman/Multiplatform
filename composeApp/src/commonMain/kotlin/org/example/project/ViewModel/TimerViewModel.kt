package org.example.project.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class TimerViewModel : ViewModel() {

    private val _remainingTime = MutableStateFlow("Загрузка...")
    val remainingTime: StateFlow<String> get() = _remainingTime.asStateFlow()

    // Укажи нужную дату и часовой пояс
    private val marathonStart: ZonedDateTime = ZonedDateTime.of(
        2025, 6, 1, 9, 0, 0, 0,
        ZoneId.of("Europe/Moscow")
    )

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (isActive) {
                val now = ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
                val duration = ChronoUnit.MILLIS.between(now, marathonStart)

                if (duration <= 0) {
                    _remainingTime.value = "Марафон начался!"
                    break
                }

                val totalSeconds = duration / 1000
                val days = totalSeconds / (60 * 60 * 24)
                val hours = (totalSeconds % (60 * 60 * 24)) / (60 * 60)
                val minutes = (totalSeconds % (60 * 60)) / 60
                val seconds = totalSeconds % 60

                val daysText = formatUnit(days, "день", "дня", "дней")
                val hoursText = formatUnit(hours, "час", "часа", "часов")
                val minutesText = formatUnit(minutes, "минута", "минуты", "минут")
                val secondsText = formatUnit(seconds, "секунда", "секунды", "секунд")

                _remainingTime.value =
                    "$daysText $hoursText $minutesText и $secondsText до старта марафона!"

                delay(1000)
            }
        }
    }

    private fun formatUnit(value: Long, singular: String, few: String, many: String): String {
        val mod100 = value % 100
        val mod10 = value % 10
        val word = when {
            mod100 in 11..14 -> many
            mod10 == 1L -> singular
            mod10 in 2..4 -> few
            else -> many
        }
        return "$value $word"
    }
}
