package org.example.project

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.ui.Screens.MainScreen

@Composable
fun App() {
    Navigator(screen = MainScreen())
}