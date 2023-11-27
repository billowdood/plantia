package com.plantia.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class HomeUiState(
    val isAutomaticWater: Boolean = false,
    val isManualWater: Boolean = false
)
class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun toggleAutomaticWater() {
        this._uiState.update { currentState ->
            currentState.copy(
                isAutomaticWater = !currentState.isAutomaticWater
            )
        }
    }
}