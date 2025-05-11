package org.example.project.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun FieldWithLabelInt(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    placeholder: String,
    fontSize: TextUnit,
    labelWidth: Float,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    labelColor: Color = Color.DarkGray,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    placeholderColor: Color = Color.LightGray,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done
    ),
    errorMessage: String? = null,
    onErrorDismiss: () -> Unit = {}
) {
    var error by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(modifier = Modifier.weight(labelWidth)) {
            Text(
                text = label,
                color = labelColor,
                textAlign = TextAlign.Start,
                fontSize = fontSize,
            )
        }

        OutlinedTextField(
            value = value.toString(),
            onValueChange = { newValue ->
                val intValue = newValue.toIntOrNull()
                if (intValue != null) {
                    error = false
                    errorText = ""
                    onValueChange(intValue)
                } else {

                    error = true
                    errorText = "Пожалуйста, введите только число"
                }
            },
            placeholder = {
                if (placeholder.isNotEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = fontSize,
                        color = placeholderColor
                    )
                }
            },
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = fontSize,
                color = textColor
            ),
            enabled = enabled,
            colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = backgroundColor,
                cursorColor = textColor,
                textColor = textColor,
                placeholderColor = placeholderColor,
                focusedBorderColor = if (error) Color.Red else Color(0xFFCCCCCC),
                unfocusedBorderColor = if (error) Color.Red else Color(0xFFCCCCCC),
                disabledBorderColor = if (error) Color.Red else Color(0xFFCCCCCC)
            ),
            keyboardOptions = keyboardOptions,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth()
                .fillMaxWidth()
        )
    }


    if (error) {
        Box(modifier = Modifier.fillMaxWidth().padding(start = 16.dp)) {
            Text(
                text = errorText,
                color = Color.Red,
                fontSize = fontSize,
                textAlign = TextAlign.Start
            )
        }
    }
}
