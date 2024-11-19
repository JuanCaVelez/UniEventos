package com.unieventos.ui.screens.admin

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.unieventos.R
import com.unieventos.model.Event
import com.unieventos.ui.components.AppButton
import com.unieventos.ui.components.DropDownMenu
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.viewmodel.EventsViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventScreen(
    onNavigationBack: () -> Unit,
    evevtId: String? = null,
    eventsViewModel: EventsViewModel,
    onNavigationHome: () -> Unit
){
    var event: Event? by remember { mutableStateOf(null) }
    val createLabel = "Crear"
    var text by remember { mutableStateOf(createLabel) }
    val context = LocalContext.current

    if (evevtId != null){
        text = createLabel
        LaunchedEffect(evevtId) {
            event = eventsViewModel.findById(evevtId)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "$text evento")},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigationBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            CreateEventForm(
                padding = PaddingValues(0.dp),
                context = context,
                eventsViewModel = eventsViewModel,
                onNavigationHome = onNavigationHome
            )
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateEventForm(
    padding: PaddingValues,
    context: Context,
    eventsViewModel: EventsViewModel,
    onNavigationHome: () -> Unit
) {

    val citys = listOf("Armenia", "Pereira", "Manizales")
    val categories = listOf("Conciertos", "Obras de teatro", "Partidos Futbol")

    var eventTitle by rememberSaveable { mutableStateOf("") }
    var eventAddress by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var dateOfEvent by rememberSaveable { mutableStateOf("") }
    var imagenEvent by rememberSaveable { mutableStateOf("") }
    var showDatePicker by rememberSaveable { mutableStateOf (false) }
    var datePickerState = rememberDatePickerState()


    val fileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            Log.e("URI", uri.toString())
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if(it){
            Toast.makeText(context, "Permiso concedido", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }

    Column (
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        TextFieldForm(
            value = eventTitle,
            onValueChange = {
                eventTitle = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventName),
            onValidate = {
                eventTitle.isBlank()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextFieldForm(
            value = eventAddress,
            onValueChange = {
                eventAddress = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventAddress),
            onValidate = {
                eventAddress.isBlank()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        DropDownMenu(
            value = city,
            onValueChange = {
                city = it
            },
            items = citys,
            placeholder = "Seleccione la ciudad",
            label = stringResource(id = R.string.city)
        )

        Spacer(modifier = Modifier.height(10.dp))

        DropDownMenu(

            value = category,
            onValueChange = {
                category = it
            },
            items =  categories,
            placeholder = "Seleccione la categoria",
            label = stringResource(id = R.string.category)
        )

        Spacer(modifier = Modifier.height(6.dp))

        TextFieldForm(
            value = description,
            onValueChange = {
                description = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventDescription),
            onValidate = {
                description.isBlank()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

        TextFieldForm(
            value = quantity,
            onValueChange = {
                quantity = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventQuantity),
            onValidate = {
                quantity.isBlank()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextFieldForm(
            value = price,
            onValueChange = {
                price = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventPrice),
            onValidate = {
                price.isBlank()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        OutlinedTextField(
            value = dateOfEvent,
            onValueChange = {},
            readOnly = true,
            placeholder = {
                Text(text = stringResource(id = R.string.eventDate))
            },
            trailingIcon = {
                IconButton(
                    onClick = {showDatePicker = true}
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DateRange,
                        contentDescription = "Icono de fecha de evento"
                    )
                }
            },
            shape = RoundedCornerShape(15.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF22d35a),
                focusedLabelColor = Color(0xFF22d35a),
                unfocusedBorderColor = Color.White,
                unfocusedLabelColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            TextFieldForm(
                modifier = Modifier.weight(0.8f),
                value = imagenEvent,
                onValueChange = {
                    imagenEvent = it
                },
                supportingText = "",
                label = stringResource(id = R.string.imageEvent),
                onValidate = {
                    imagenEvent.isBlank()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Button(
                onClick = {
                    val permissionCheckResult = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        ContextCompat.checkSelfPermission(
                            context,
                            android.Manifest.permission.READ_MEDIA_IMAGES
                        )
                    }else{
                        ContextCompat.checkSelfPermission(
                            context,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    }

                    if(permissionCheckResult == PackageManager.PERMISSION_GRANTED){
                        fileLauncher.launch("image/*")
                    }else{
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                            permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                        }else{
                            permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                    }
                }

            ) {
                Icon(
                    imageVector = Icons.Rounded.Upload,
                    contentDescription = null
                )
            }
        }


        Spacer(modifier = Modifier.height(10.dp))

        val create = stringResource(id = R.string.eventCreate)

        AppButton(
            onClick = {
                eventsViewModel.createEvent(
                    Event(
                        id = "",
                        title = eventTitle,
                        address = eventAddress,
                        city = city,
                        category = category,
                        description = description,
                        quantity = quantity,
                        price = price,
                        date = dateOfEvent,
                        imageUrl = imagenEvent,
                        //locations = locations
                    )
                )
                Toast.makeText(context, create, Toast.LENGTH_SHORT).show()
                onNavigationHome()
            },
            text = stringResource(id = R.string.create)
        )

        if(showDatePicker){
            DatePickerDialog(
                onDismissRequest = {showDatePicker = false},
                confirmButton = {
                    TextButton(
                        onClick = {
                            val selectedDate = datePickerState.selectedDateMillis
                            if(selectedDate != null){
                                val date = Date(selectedDate)
                                val formattedDate = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(date)
                                dateOfEvent = formattedDate
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text(text = stringResource(id = R.string.confirmLabel))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {showDatePicker = false}
                    ) {
                        Text(text = stringResource(id = R.string.cancelLabel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

