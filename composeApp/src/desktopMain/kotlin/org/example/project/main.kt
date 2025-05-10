package org.example.project

import androidx.compose.ui.window.application
import androidx.compose.ui.window.Window
import org.example.project.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Marathon Skills 2016"
    ) {
        App()
    }
}