package org.example.project.ViewModel

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.skia.Image
import java.io.File
import java.nio.file.Files

@Composable
actual fun ImageFromPath(path: String, modifier: Modifier) {
    val imageBitmap: ImageBitmap? = try {
        if (path.isBlank()) {
            val resourceStream = Thread.currentThread().contextClassLoader.getResourceAsStream("drawable/basicPhoto.png")
            val bytes = resourceStream.readAllBytes()
            Image.makeFromEncoded(bytes).toComposeImageBitmap()
        } else {
            val file = File(path)
            if (file.exists()) {
                val bytes = Files.readAllBytes(file.toPath())
                Image.makeFromEncoded(bytes).toComposeImageBitmap()
            } else null
        }
    } catch (e: Exception) {
        println("Ошибка загрузки изображения: ${e.message}")
        null
    }

    if (imageBitmap != null) {
        Image(
            painter = BitmapPainter(imageBitmap),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Box(modifier = modifier.fillMaxSize())
    }
}