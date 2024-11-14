package com.unieventos.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import okhttp3.internal.wait

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    value: String,
    shape: Shape = RoundedCornerShape(15.dp),
    width: Dp = 350.dp,
    onValueChange: (String) -> Unit,
    items: List<String>,
    placeholder: String,
    label: String
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var isLabelColored by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
            isLabelColored = expanded
        }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding()
                .menuAnchor()
                .width(width),
            value = value,
            shape = shape,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = placeholder, color = Color.White)
            },
            label = {
                Text(
                    text = label,
                    color = if (isLabelColored && value != "Seleccione") Color(0xFF42d322) else Color.White // Cambiar color según expansión y selección
                )
            },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF42d322),
                focusedLabelColor = Color(0xFF42d322),
                unfocusedBorderColor = Color.White,
                unfocusedLabelColor = Color.White
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                isLabelColored = false
            }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onValueChange(item)
                        expanded = false
                        isLabelColored = false
                    }
                )
            }
        }
    }
}