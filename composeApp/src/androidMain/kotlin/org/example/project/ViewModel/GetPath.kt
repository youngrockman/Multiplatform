package org.example.project.ViewModel

import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileOutputStream

@Composable
actual fun GetPath(onPathSelected: (String) -> Unit) {
    val context = LocalContext.current
    var launched by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                val inputStream = context.contentResolver.openInputStream(uri)
                val fileName = getFileName(context.contentResolver, uri) ?: "temp_image.jpg"
                val file = File(context.cacheDir, fileName)
                val outputStream = FileOutputStream(file)
                inputStream?.copyTo(outputStream)
                inputStream?.close()
                outputStream.close()
                onPathSelected(file.absolutePath)
            }
        }
    )

    LaunchedEffect(Unit) {
        if (!launched) {
            launched = true
            launcher.launch("image/*")
        }
    }
}

fun getFileName(contentResolver: android.content.ContentResolver, uri: Uri): String? {
    val returnCursor = contentResolver.query(uri, null, null, null, null)
    returnCursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (it.moveToFirst()) {
            return it.getString(nameIndex)
        }
    }
    return null
}