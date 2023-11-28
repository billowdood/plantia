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
/*class DashboardViewModel(
    private val plantRepository: PlantRepository
) : ViewModel() {
    val plantListLive: MutableLiveData<DashboardUiState> = MutableLiveData()

    fun fetchPlants(): CollectionReference {
        return plantRepository.fetchPlants()
    }

    private fun addPlant(plant: Plant) {
        plantListLive.value?.plantList?.add(plant)

        Log.d("PlantIA", "Debug message")
    }
}*/
class DashboardViewModel: ViewModel() {
    val plantListLive: MutableLiveData<DashboardUiState> = MutableLiveData()
    private val plantRepository: PlantRepository = PlantRepository()

    fun addPlant(plant: Plant) {
        plantListLive.value?.plantList?.add(plant)

        Log.d("PlantIA", "Plant added")
    }
    fun fetchPlants(): CollectionReference {
        return plantRepository.fetchPlants()
    }
}