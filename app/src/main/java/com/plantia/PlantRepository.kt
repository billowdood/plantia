package com.plantia

import com.google.firebase.firestore.CollectionReference

/*class PlantRepository(
    private val plantDataSource: PlantDataSource
) {
    fun fetchPlants(): CollectionReference {
        return plantDataSource.fetchPlants()
    }
}*/
class PlantRepository() {
    private val plantDataSource: PlantDataSource = PlantDataSource()

    fun fetchPlants(): CollectionReference {
        return plantDataSource.fetchPlants()
    }
}