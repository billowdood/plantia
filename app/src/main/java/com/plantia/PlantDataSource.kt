package com.plantia

import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

data class Plant (
    val Descripcion: String = "",
    val ambMax: Int = 0,
    val ambMin: Int = 0,
    val nombrePlanta: String = "",
    val tempMax: Int = 0,
    val tempMin: Int = 0,
    val tierraMax: Int = 0,
    val tierraMin: Int = 0
)
class PlantDataSource{

    private val db: FirebaseFirestore = Firebase.firestore
    fun fetchPlants(): CollectionReference {
        return db.collection("Plantas")
    }
}