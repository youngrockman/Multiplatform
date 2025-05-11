package org.example.project.ViewModel

import androidx.compose.runtime.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter

@Composable
actual fun GetPath(onPathSelected: (String) -> Unit) {
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            JFileChooser().apply {
                dialogTitle = "Выберите изображение"
                fileSelectionMode = JFileChooser.FILES_ONLY


                val filter = FileNameExtensionFilter(
                    "Изображения (*.jpg, *.jpeg, *.png, *.gif)",
                    "jpg", "jpeg", "png", "gif"
                )
                addChoosableFileFilter(filter)
                fileFilter = filter

                val result = showOpenDialog(null)
                val path = if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile?.absolutePath ?: ""
                } else {
                    ""
                }

                onPathSelected(path)
            }
        }
    }
}