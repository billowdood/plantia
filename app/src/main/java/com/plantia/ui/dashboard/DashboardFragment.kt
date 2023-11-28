package com.plantia.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.plantia.Plant
import com.plantia.PlantRepository
import com.plantia.databinding.FragmentDashboardBinding

class DashboardFragment: Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

       viewModel.fetchPlants()
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("PlantIA", "${document.id} => ${document.data}")
                    val plant = document.toObject(Plant::class.java)

                    viewModel.addPlant(plant)
                }
            }
            .addOnFailureListener { exception ->
                Log.w("PlantIA", "Error getting documents.", exception)
            }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}