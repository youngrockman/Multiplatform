package org.example.project.ViewModel

import androidx.compose.runtime.Composable

@Composable
expect fun DatePickerField(
    onDateSelected: (String) -> Unit,
    trigger: Boolean,
    onDismissRequest: () -> Unit
)
