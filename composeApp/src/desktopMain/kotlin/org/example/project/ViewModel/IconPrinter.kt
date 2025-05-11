package org.example.project.ViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.foundation.Image
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.loadImageBitmap
import java.io.IOException
import java.io.InputStream

@Composable
actual fun IconPrinter(resourceName: String, modifier: Modifier) {
    val imageBitmap = remember(resourceName) {
        loadImageResource("drawable/$resourceName")
    }

    imageBitmap?.let { bitmap ->
        Image(
            painter = BitmapPainter(bitmap),
            contentDescription = "Иконка $resourceName",
            modifier = modifier,
            contentScale = ContentScale.Fit
        )
    }
}

private fun loadImageResource(path: String): ImageBitmap? {
    return try {
        val extensions = listOf(".jpg", ".jpeg", ".png", ".webp")
        val resourceStream = findResourceWithExtensions(path, extensions)

        resourceStream?.use { stream ->
            loadImageBitmap(stream)
        }
    } catch (e: IOException) {
        System.err.println("Ошибка загрузки изображения: ${e.message}")
        null
    }
}

private fun findResourceWithExtensions(basePath: String, extensions: List<String>): InputStream? {
    return extensions.firstNotNullOfOrNull { ext ->
        Thread.currentThread()
            .contextClassLoader
            .getResourceAsStream("$basePath$ext")
    }
}