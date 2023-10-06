package com.example.emptyactivitytest.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.emptyactivitytest.ui.navigation.AppScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyViewModel : ViewModel() {

    private val myModel = MyModel()

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()
    fun updateScreenName(route: String?) {
        _uiState.update { currentState ->
            currentState.copy(
                currentScreenName = myModel.getCurrentScreen(route = route)
            )
        }
    }

    data class UiState(
        val currentScreenName: Int = AppScreen.MainScreen.title,
    )
}

