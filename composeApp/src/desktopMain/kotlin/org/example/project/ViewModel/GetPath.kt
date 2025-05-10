package org.example.project.ViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.swing.JFileChooser

@Composable
actual fun GetPath(onPathSelected: (String) -> Unit) {
    LaunchedEffect(Unit) {
        val selectedPath = withContext(Dispatchers.IO) {
            val chooser = JFileChooser()
            chooser.dialogTitle = "Выберите изображение"
            chooser.fileSelectionMode = JFileChooser.FILES_ONLY

            val result = chooser.showOpenDialog(null)
            if (result == JFileChooser.APPROVE_OPTION) {
                val file: File = chooser.selectedFile
                file.absolutePath
            } else {
                ""
            }
        }

        onPathSelected(selectedPath)
    }
}