package com.unieventos.ui.screens.admin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.rounded.LocalActivity
import androidx.compose.material.icons.rounded.Loyalty
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unieventos.ui.components.FloatingButton
import com.unieventos.ui.screens.admin.tabs.CouponScreen
import com.unieventos.ui.screens.admin.tabs.EventsScreen
import com.unieventos.ui.screens.admin.navigation.ItemTabAdmin
import com.unieventos.ui.screens.admin.navigation.NavHostAdmin
import com.unieventos.ui.screens.admin.tabs.ProfileScreen
import com.unieventos.viewmodel.EventsViewModel
import com.unieventos.viewmodel.UsersViewModel
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild


@Composable
fun HomeAdminScreen(
    onNavigateToEventDetailAdmin: (String) -> Unit,
    onNavigateToCreateEvent: () -> Unit,
    onLogout: () -> Unit,
    eventsViewModel: EventsViewModel,
    usersViewModel: UsersViewModel
) {
    val navController = rememberNavController()
    val hazeState = remember { HazeState() }
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopBarHomeAdmin(
                hazeState = hazeState,
                onLogout = onLogout,
            )
        },
        bottomBar = {
            BottomBarHomeAdmin(
                navController = navController
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHostAdmin(
                navController = navController,
                eventsViewModel = eventsViewModel,
                usersViewModel = usersViewModel
            ) {

            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.padding(end = 16.dp, bottom = 16.dp)
                ) {
                    FloatingButton(
                        onClick = { onNavigateToCreateEvent() }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHomeAdmin(
    hazeState: HazeState,
    onLogout: () -> Unit,
){
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.largeTopAppBarColors(Color.Transparent),
        modifier = Modifier
            .hazeChild(state = hazeState),
        title = {
            Text(text = "UniEventos Admin")
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


@Composable
fun BottomBarHomeAdmin(
    navController: NavHostController,
) {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Loyalty,
                    contentDescription = null,
                )
            },
            onClick = {
                navController.navigate(ItemTabAdmin.TabCoupons) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            },
            label = {
                Text(
                    text = "Cupones",
                )
            },
            selected = false
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Rounded.LocalActivity,
                    contentDescription = null,
                )
            },
            onClick = {
                navController.navigate(ItemTabAdmin.TabEvents) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            },
            label = {
                Text(
                    text = "Eventos",
                )
            },
            selected = false
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = null,
                )
            },
            onClick = {
                navController.navigate(ItemTabAdmin.TabProfile) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = true }
                }
            },
            label = {
                Text(
                    text = "Perfil",
                )
            },
            selected = false
        )
    }
}







