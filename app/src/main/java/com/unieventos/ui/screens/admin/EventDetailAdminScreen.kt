package com.unieventos.ui.screens.admin

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.unieventos.R
import com.unieventos.ui.components.TextFieldForm
import com.unieventos.viewmodel.EventsViewModel

@Composable
fun EventDetaiLAdminScreen(
    eventId : String,
    eventsViewModel: EventsViewModel,
    onNavigationBack: () -> Unit
){
    val event = eventsViewModel.findById(eventId)
    requireNotNull(event)

    Scaffold (
        topBar = {
            TopAppBarAdminDetail(
                title = event.title,
                onNavigationBack = onNavigationBack
            )
        },
    ){padding ->

        Column(
            modifier = Modifier.padding(padding)
        ) {
            val model = ImageRequest.Builder(LocalContext.current)
                .data(event.imageUrl)
                .crossfade(true)
                .build()

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = model,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Column(

            ) {

            }
        }

    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun editEventForm(
    padding: PaddingValues,
    context: Context,
    eventsViewModel: EventsViewModel,
    onNavigationHome: () -> Unit
){
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

    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextFieldForm(
            value = eventAddress,
            onValueChange = {
                eventTitle = it
            },
            supportingText = "",
            label = stringResource(id = R.string.eventName),
            onValidate = {
                eventAddress.isBlank()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarAdminDetail(
    title: String,
    onNavigationBack: () -> Unit
){
    TopAppBar(
        title = {
            Text(text = title)
        },
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
