package org.example.project.ViewModel

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import org.example.project.R

@Composable
actual fun IconPrinter(resourceName: String, modifier: Modifier) {
    val resId = when (resourceName) {
        "calendar" -> R.drawable.map
        else -> return
    }

    Image(
        painter = painterResource(id = resId),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.FillBounds
    )
}