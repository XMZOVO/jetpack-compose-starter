package com.xmzovo.example.presentation.composable

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.navigation.popBackStack
import com.xmzovo.example.presentation.NavGraphs
import com.xmzovo.example.presentation.appCurrentDestinationAsState
import com.xmzovo.example.presentation.destinations.DirectionDestination
import com.xmzovo.example.presentation.startAppDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo
import com.xmzovo.example.R
import com.xmzovo.example.presentation.destinations.HomeScreenDestination
import com.xmzovo.example.presentation.destinations.ProfileScreenDestination

enum class BottomBarDestination(
    val direction: DirectionDestination,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(HomeScreenDestination, Icons.Default.Home, R.string.home),
    Profile(ProfileScreenDestination, Icons.Default.Person, R.string.profile),
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    NavigationBar {
        BottomBarDestination.values().forEach { destination ->
            val currentDestination = navController.appCurrentDestinationAsState().value
                ?: NavGraphs.main.startAppDestination
            NavigationBarItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    if (currentDestination == destination.direction) {
                        // When we click again on a bottom bar item and it was already selected
                        // we want to pop the back stack until the initial destination of this bottom bar item
                        navController.popBackStack(destination.direction, false)
                        return@NavigationBarItem
                    }
                    navController.navigate(destination.direction) {
                        // Pop up to the root of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(NavGraphs.main) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        destination.icon,
                        contentDescription = stringResource(destination.label)
                    )
                },
                label = { Text(stringResource(destination.label)) },
            )
        }
    }
}
