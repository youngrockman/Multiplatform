package org.example.project.ViewModel

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
actual fun DatePickerField(
    onDateSelected: (String) -> Unit,
    trigger: Boolean,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }


    if (trigger) {
        LaunchedEffect(trigger) {
            DatePickerDialog(
                context,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    val formatted = "%02d.%02d.%04d".format(dayOfMonth, month + 1, year)
                    onDateSelected(formatted)
                    onDismissRequest()
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}
