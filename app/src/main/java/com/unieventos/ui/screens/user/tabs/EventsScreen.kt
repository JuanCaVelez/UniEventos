package com.unieventos.ui.screens.user.tabs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unieventos.ui.components.ItemEvento
import com.unieventos.viewmodel.EventsViewModel

@Composable
fun EventsScreen(
    onNavigateToDetail: (String) -> Unit,
    onNavigateToCart: (String) -> Unit,
    eventsViewModel: EventsViewModel,
){
    val events = eventsViewModel.events.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(events){ event ->
            ItemEvento(
                event = event,
                onNavigateToDetail = onNavigateToDetail,
                onNavigateToCart = onNavigateToCart
            )
        }
    }
}