package com.plantia.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AppData(
    var nivel: Int = 0,
    var humedadAmbiental: Int = 0,
    var humedadPlanta: Int = 0,
    var humedadSueloPlantaMax: Int = 0,
    var humedadSueloPlantaMin: Int = 0,
    var presion: Int = 0,
    var regarAhora: Int = 0,
    var regarManual: Int = 0,
    var temperaturaBMP: Int = 0,
    var temperaturaDHT: Int = 0
)
data class HomeUiState(
    val isAutomaticWater: Boolean = false,
    val isWaterNow: Boolean = false
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

    fun toggleWaterNow() {
        this._uiState.update { currentState ->
            currentState.copy(
                isWaterNow = !currentState.isWaterNow
            )
        }
    }

    fun setAutomaticWater(isAutomaticWater: Boolean) {
        this._uiState.update { currentState ->
            currentState.copy(
                isAutomaticWater = isAutomaticWater
            )
        }
    }

    fun setWaterNow(waterNow: Boolean) {
        this._uiState.update { currentState ->
            currentState.copy(
                isWaterNow = waterNow
            )
        }
    }
}