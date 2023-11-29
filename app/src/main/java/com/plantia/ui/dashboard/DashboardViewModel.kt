package com.plantia.ui.dashboard

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.plantia.Plant
import com.plantia.PlantRepository

data class DashboardUiState(
    val plantList: MutableList<Plant> = mutableListOf<Plant>()
)

class DashboardViewModel: ViewModel() {
    val uiState: DashboardUiState = DashboardUiState()
    private val plantRepository: PlantRepository = PlantRepository()

    fun addPlant(plant: Plant) {
        uiState.plantList.add(plant)
    }
    fun fetchPlants(): CollectionReference {
        return plantRepository.fetchPlants()
    }

    fun plants(): MutableList<Plant> {
        return  uiState.plantList
    }
}