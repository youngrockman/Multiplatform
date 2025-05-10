package org.example.project.ViewModel

import androidx.compose.runtime.*
import java.text.SimpleDateFormat
import javax.swing.*
import java.util.*

@Composable
actual fun DatePickerField(
    onDateSelected: (String) -> Unit,
    trigger: Boolean,
    onDismissRequest: () -> Unit
) {
    if (trigger) {
        LaunchedEffect(Unit) {
            val dialog = JDialog()
            dialog.title = "Выбор даты"
            val model = SpinnerDateModel()
            val spinner = JSpinner(model)
            val panel = JPanel()
            val button = JButton("OK")
            panel.add(spinner)
            panel.add(button)
            dialog.contentPane = panel
            dialog.setSize(200, 100)

            button.addActionListener {
                val date = model.date
                val formatted = SimpleDateFormat("dd.MM.yyyy").format(date)
                onDateSelected(formatted)
                dialog.dispose()
                onDismissRequest()
            }

            dialog.isVisible = true
        }
    }
}