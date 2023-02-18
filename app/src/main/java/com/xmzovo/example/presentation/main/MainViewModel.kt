package com.xmzovo.example.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MainState(
    val text: String = "Hello World!"
)

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private var _state by mutableStateOf(MainState())
    val state: MainState
        get() = _state

    fun changeText(text: String) {
        _state = _state.copy(text = text)
    }
}