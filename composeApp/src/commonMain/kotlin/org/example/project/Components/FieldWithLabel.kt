package org.example.project.Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun FieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    fontSize: TextUnit,
    labelWidth: Float,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    labelColor: Color = Color.DarkGray,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    placeholderColor: Color = Color.LightGray,
) {
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
                modifier = Modifier
            )
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
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
                focusedBorderColor = Color(0xFFCCCCCC),
                unfocusedBorderColor = Color(0xFFCCCCCC),
                disabledBorderColor = Color(0xFFCCCCCC)
            ),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth()
                .fillMaxWidth()
        )
    }
}
