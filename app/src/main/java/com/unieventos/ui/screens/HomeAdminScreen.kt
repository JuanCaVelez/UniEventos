package com.unieventos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.unieventos.model.Event
import com.unieventos.ui.components.ItemEventoAdmin
import com.unieventos.viewmodel.EventsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAdminScreen(
    onNavigateToEditProfile: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    eventsViewModel: EventsViewModel,
    onLogout: () -> Unit,
    onNavigateToCreateEvent: () -> Unit,
    onNavigateToCreateCoupon: () -> Unit
) {
    val events = eventsViewModel.events.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Eventos Admin") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateToEditProfile() }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onLogout() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.Logout,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            ListEvents(
                events = events.value,
                onNavigateToDetail = onNavigateToDetail
            )
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
                ) {
                    FloatingActionButton(onClick = { onNavigateToCreateEvent() }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Create Event"
                        )
                    }

                    FloatingActionButton(onClick = { onNavigateToCreateCoupon() }) {
                        Icon(
                            imageVector = Icons.Default.CardGiftcard,
                            contentDescription = "Cupons"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ListEvents(
    events: List<Event>,
    onNavigateToDetail: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(events) { event ->
            ItemEventoAdmin(
                event = event,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}





