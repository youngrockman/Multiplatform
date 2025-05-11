package org.example.project.ViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import java.io.File
import java.io.FileInputStream
import java.io.IOException

@Composable
actual fun ImageFromPath(path: String, modifier: Modifier) {
    val imageBitmap = remember(path) { loadImageBitmap(path) }

    if (imageBitmap != null) {
        Image(
            painter = BitmapPainter(imageBitmap),
            contentDescription = "Загруженное изображение",
            modifier = modifier,
            contentScale = ContentScale.Crop,
            alpha = if (imageBitmap.isValid()) 1f else 0.5f
        )
    } else {
        FallbackImage(modifier)
    }
}

@Composable
private fun FallbackImage(modifier: Modifier) {
    Image(
        painter = painterResource("drawable/basicPhoto.png"),
        contentDescription = "Изображение по умолчанию",
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

private fun loadImageBitmap(path: String): ImageBitmap? {
    return when {
        path.isBlank() -> loadDefaultImage()
        else -> try {
            File(path).takeIf { it.exists() }?.let { file ->
                FileInputStream(file).use { stream ->
                    org.jetbrains.skia.Image.makeFromEncoded(stream.readAllBytes())
                        .asImageBitmap()
                }
            }
        } catch (e: IOException) {
            System.err.println("Ошибка загрузки изображения: ${e.message}")
            null
        }
    }
}

private fun loadDefaultImage(): ImageBitmap? {
    return try {
        Thread.currentThread().contextClassLoader
            .getResourceAsStream("drawable/basicPhoto.png")
            ?.use { stream ->
                org.jetbrains.skia.Image.makeFromEncoded(stream.readAllBytes())
                    .asImageBitmap()
            }
    } catch (e: Exception) {
        System.err.println("Ошибка загрузки изображения по умолчанию: ${e.message}")
        null
    }
}

private fun ImageBitmap.isValid(): Boolean {
    return this.width > 0 && this.height > 0
}