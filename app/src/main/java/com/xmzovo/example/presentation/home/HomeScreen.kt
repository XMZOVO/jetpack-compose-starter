package com.xmzovo.example.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.xmzovo.example.presentation.destinations.PageScreenDestination
import com.xmzovo.example.presentation.main.MainNavGraph
import com.xmzovo.example.presentation.main.MainViewModel

@MainNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    vm: MainViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    navToPage:(message:String)->Unit
) {

    val state = vm.state

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(onClick = {
            vm.changeText(if (state.text == "Hello World!") "Hello Compose!" else "Hello World!")
        }) {
            Text(text = state.text)
        }
        ElevatedButton(onClick = {
            navToPage(state.text)
        }) {
            Text(text = "NavToPage")
        }
    }
}