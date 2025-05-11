package org.example.project.ViewModel

import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

@Composable
actual fun GetPath(onPathSelected: (String) -> Unit) {
    val context = LocalContext.current
    var launched by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                try {
                    val filePath = saveFileToCache(context, uri)
                    onPathSelected(filePath)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
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
    contentResolver.query(uri, null, null, null, null)?.use { cursor ->
        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (cursor.moveToFirst()) {
            return cursor.getString(nameIndex)
        }
    }
    return null
}


private fun saveFileToCache(context: android.content.Context, uri: Uri): String {
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    val fileName = getFileName(context.contentResolver, uri) ?: "temp_image.jpg"
    val file = File(context.cacheDir, fileName)
    inputStream?.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }
    return file.absolutePath
}
