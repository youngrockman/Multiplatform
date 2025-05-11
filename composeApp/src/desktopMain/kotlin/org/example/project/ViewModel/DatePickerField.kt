package org.example.project.ViewModel

import androidx.compose.runtime.*
import java.text.SimpleDateFormat
import javax.swing.*
import java.util.*
import com.toedter.calendar.JDateChooser

@Composable
actual fun DatePickerField(
    onDateSelected: (String) -> Unit,
    trigger: Boolean,
    onDismissRequest: () -> Unit
) {
    var dialog by remember { mutableStateOf<JDialog?>(null) }

    LaunchedEffect(trigger) {
        if (trigger && dialog == null) {
            val dateChooser = JDateChooser().apply {
                date = Date()
                isVisible = true
            }

            val pickerDialog = JDialog().apply {
                title = "Выберите дату"
                contentPane = dateChooser
                setSize(400, 300)
                setLocationRelativeTo(null)
                isModal = true

                addWindowListener(object : java.awt.event.WindowAdapter() {
                    override fun windowClosing(e: java.awt.event.WindowEvent?) {
                        onDismissRequest()
                    }
                })
            }

            dateChooser.addPropertyChangeListener("date") { e ->
                if (e.newValue != null) {
                    val format = SimpleDateFormat("dd.MM.yyyy")
                    onDateSelected(format.format(e.newValue))
                    pickerDialog.dispose()
                    onDismissRequest()
                }
            }

            dialog = pickerDialog
            pickerDialog.isVisible = true
        } else if (!trigger) {
            dialog?.dispose()
            dialog = null
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            dialog?.dispose()
            dialog = null
        }
    }
}