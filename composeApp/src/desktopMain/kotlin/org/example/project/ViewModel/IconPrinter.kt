package org.example.project.ViewModel

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.skia.Image
import java.io.IOException

@Composable
actual fun IconPrinter(resourceName: String, modifier: Modifier) {
    val imageBitmap = try {
        val path = "drawable/${resourceName}.jpg"
        val stream = Thread.currentThread().contextClassLoader.getResourceAsStream(path)
        if (stream != null) {
            Image.makeFromEncoded(stream.readAllBytes()).toComposeImageBitmap()
        } else null
    } catch (e: IOException) {
        println("Ошибка загрузки иконки: ${e.message}")
        null
    }

    if (imageBitmap != null) {
        Image(
            painter = BitmapPainter(imageBitmap),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.FillBounds
        )
    }
}