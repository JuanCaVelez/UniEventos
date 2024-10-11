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
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.unieventos.ui.components.ItemEvento
import com.unieventos.model.Event
import com.unieventos.viewmodel.EventsViewModel
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun HomeScreen(
    onNavigateToEditProfile: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    eventsViewModel: EventsViewModel,
    onLogout: () -> Unit,
    onNavigateToCart: () -> Unit,
    onNavigateToUsersCoupons: () -> Unit
){

    val hazeState = remember{HazeState()}
    val events = eventsViewModel.events.collectAsState()

    Scaffold (
        topBar = {
            TopBarHome(
                hazeState = hazeState,
                onLogout = onLogout,
                onNavigateToEditProfile = onNavigateToEditProfile
            )
        },
    ){ paddingValues ->
        ListEvents(
            events = events.value,
            paddingValues = paddingValues,
            onNavigateToDetail = onNavigateToDetail,
            hazeState = hazeState
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
                FloatingActionButton(onClick = { onNavigateToCart() }) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null
                    )
                }

                FloatingActionButton(onClick = { onNavigateToUsersCoupons()}) {
                    Icon(
                        imageVector = Icons.Default.CardGiftcard,
                        contentDescription = null
                    )
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(
    hazeState: HazeState,
    onLogout: () -> Unit,
    onNavigateToEditProfile: () -> Unit
){

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { onNavigateToEditProfile() }) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(Color.Transparent),
        modifier = Modifier
            .hazeChild(state = hazeState),
        title =  {
            Text(text = "UniEventos")
        },
        actions = {
            IconButton(
                onClick = {onLogout()}
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Logout,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun ListEvents(
    events: List<Event>,
    paddingValues: PaddingValues,
    onNavigateToDetail: (String) -> Unit,
    hazeState: HazeState
){

    LazyColumn (
        modifier = Modifier
            .haze(
                state = hazeState,
                style = HazeDefaults.style(backgroundColor = MaterialTheme.colorScheme.surface),
            ),
        contentPadding = paddingValues
    ){
        items(events){ event ->
            ItemEvento(
                event = event,
                onNavigateToDetail = onNavigateToDetail
            )
        }
    }
}

