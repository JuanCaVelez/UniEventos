package com.unieventos.ui.screens.admin.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unieventos.model.Event
import com.unieventos.ui.components.ItemEventoAdmin
import com.unieventos.viewmodel.EventsViewModel


@Composable
fun EventsScreen(
    onNavigateToEventDetailAdmin: (String) -> Unit,
    eventsViewModel: EventsViewModel
) {

    val events = eventsViewModel.events.collectAsState().value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(events) { event ->
            ItemEventoAdmin(
                event = event,
                onNavigateToEventDetailAdmin = onNavigateToEventDetailAdmin
            )
        }
    }
}