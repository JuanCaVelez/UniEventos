package com.unieventos.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.unieventos.ui.components.ItemEvento
import com.unieventos.ui.model.Event

@Composable
fun HomeScreen(
    onNavigateToCart: () -> Unit
){


    Scaffold (
        topBar = {
            TopBarHome()
        }
    ){ paddingValues ->
        ListEvents(
            paddingValues = paddingValues
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(){

    CenterAlignedTopAppBar(
        title =  {
            Text(text = "UniEventos")
        }
    )
}

@Composable
fun ListEvents(paddingValues: PaddingValues){

    LazyColumn (
        contentPadding = paddingValues
    ){
        items(getEventsList()){ event ->
            ItemEvento(
                nombre = event.tile,
                ciudad = event.city
            )
        }
    }
}

fun getEventsList(): List<Event>{

    return listOf(
        Event(
            id = "12",
            tile = "Concierto FEID",
            date = "Mayo 02",
            city = "Medellin",
            image = "test"
        ),
        Event(
            id = "23",
            tile = "Obra de teatro",
            date = "Septiembre 15",
            city =  "Bogotá",
            image = "test"
        ),
        Event(
            id = "50",
            tile = "Once Caldas vs Deportivo Peréira",
            date = "Octubre 23",
            city = "Manizales",
            image = "test"
        ),
        Event(
            id = "89",
            tile = "Concierto Cris Valencia",
            date = "Junio 05",
            city = "Cali",
            image = "test"
        ),
    )
}