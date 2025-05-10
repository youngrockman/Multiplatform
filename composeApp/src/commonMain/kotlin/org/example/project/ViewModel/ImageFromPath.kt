package org.example.project.ViewModel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun ImageFromPath(path: String = "", modifier: Modifier = Modifier)
