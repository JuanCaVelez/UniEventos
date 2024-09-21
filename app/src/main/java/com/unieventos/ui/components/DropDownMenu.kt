package com.unieventos.ui.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
        value: String,
        onValueChange: (String) -> Unit,
        items: List<String>,
    ){

    var expanded by rememberSaveable { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {expanded = !expanded}
    ) {
        OutlinedTextField(
            modifier = Modifier.menuAnchor(),
            value = value,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = "Seleccione la ciudad")
            },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)}
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false}
        ) {

            items.forEach{ item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onValueChange(item)
                        expanded = false
                    }
                )
            }
        }
    }
}