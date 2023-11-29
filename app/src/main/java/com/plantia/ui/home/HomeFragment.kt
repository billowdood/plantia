package com.plantia.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.plantia.databinding.FragmentHomeBinding
import com.plantia.viewmodel.AppData
import com.plantia.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private var textIsAutomaticWater: TextView? = null
    private var btnToggleAutomaticWater: Button? = null
    private var textWaterNow: TextView? = null
    private var btnWaterNow: Button? = null

    private val database = Firebase.database
    private val myRef = database.getReference("datosApp")
    private var appData: AppData? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.setViewModel()

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        this.setUiElements()
        this.setHandlers()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                appData = dataSnapshot.getValue<AppData>()

                // regarManual 0 (apagado) significa que se riega en automatico
                val automaticWater = appData?.regarManual == 0
                val waterNow = appData?.regarAhora == 1

                viewModel.setAutomaticWater(automaticWater)
                viewModel.setWaterNow(waterNow)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setHandlers() {
        this.btnToggleAutomaticWater?.setOnClickListener {
            appData?.regarManual = if (appData?.regarManual == 0) 1 else 0

            myRef.setValue(appData)
            viewModel.toggleAutomaticWater()
        }

        this.btnWaterNow?.setOnClickListener {
            appData?.regarAhora = if (appData?.regarAhora == 0) 1 else 0

            myRef.setValue(appData)
            viewModel.toggleWaterNow()

            Log.d("PlantIA","$appData?.regarAhora")
        }
    }

    private fun setUiElements() {
        this.textIsAutomaticWater = binding.textHomeToggleAutomaticWater
        this.btnToggleAutomaticWater = binding.btnHomeToggleAutomaticWater
        this.textWaterNow = binding.textHomeWaterNow
        this.btnWaterNow = binding.btnHomeWaterNow
    }

    private fun setViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // Update UI elements
                    textIsAutomaticWater?.text  = automaticWaterText(it.isAutomaticWater)
                    textWaterNow?.text = waterNowText(it.isWaterNow)
                }
            }
        }
    }
}