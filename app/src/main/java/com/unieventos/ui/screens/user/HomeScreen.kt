package com.unieventos.ui.screens.user


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.compose.rememberNavController
import com.unieventos.ui.screens.user.bottomBar.BottomBarUser
import com.unieventos.ui.screens.user.navigation.NavHostUser
import com.unieventos.viewmodel.CartViewModel
import com.unieventos.viewmodel.EventsViewModel
import com.unieventos.viewmodel.UsersViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun HomeScreen(
    onNavigateToDetail: (String) -> Unit,
    onNavigateToCart: (String) -> Unit,
    onLogout: () -> Unit,
    onNavigateToPasswordRecovery: () -> Unit,
    eventsViewModel: EventsViewModel,
    usersViewModel: UsersViewModel,
    cartViewModel: CartViewModel
) {

    val navController = rememberNavController()
    val hazeState = remember { HazeState() }
    val events = eventsViewModel.events.collectAsState()


    Scaffold(
        topBar = {
            TopBarHome(
                hazeState = hazeState,
                onLogout = onLogout,
            )
        },
        bottomBar = {
            BottomBarUser(
                navController = navController,
                hazeState = hazeState,
                cartViewModel = cartViewModel
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHostUser(
                navController = navController,
                eventsViewModel = eventsViewModel,
                usersViewModel = usersViewModel,
                cartViewModel = cartViewModel,
                onNavigateToDetail = onNavigateToDetail,
                onNavigateToCart = onNavigateToCart
            ) {

            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(
    hazeState: HazeState,
    onLogout: () -> Unit,
) {

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(Color.Transparent),
        modifier = Modifier
            .hazeChild(state = hazeState),
        title = {
            Text(text = "UniEventos")
        },
        actions = {
            IconButton(
                onClick = { onLogout() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Logout,
                    contentDescription = null
                )
            }
        }
    )
}

