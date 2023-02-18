package com.xmzovo.example.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.xmzovo.example.presentation.NavGraphs
import com.xmzovo.example.presentation.composable.BottomBar
import com.xmzovo.example.presentation.destinations.HomeScreenDestination
import com.xmzovo.example.presentation.destinations.PageScreenDestination
import com.xmzovo.example.presentation.home.HomeScreen

@RootNavGraph
@NavGraph
annotation class MainNavGraph(
    val start: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(
    vm: MainViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Main") })
        },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            DestinationsNavHost(navGraph = NavGraphs.main, navController = navController) {
                composable(HomeScreenDestination) {
                    HomeScreen(
                        navigator = destinationsNavigator
                    ) { message ->
                        navigator.navigate(PageScreenDestination(message))
                    }
                }
            }
        }
    }
}