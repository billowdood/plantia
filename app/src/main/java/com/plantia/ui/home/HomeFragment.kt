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
import com.plantia.databinding.FragmentHomeBinding
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
    private var btnWaterNow: Button? = null

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setHandlers() {
        this.btnToggleAutomaticWater?.setOnClickListener {
            viewModel.toggleAutomaticWater()
            Log.d("PlantIA","Toggled automatic water")
        }
    }

    private fun setUiElements() {
        this.textIsAutomaticWater = binding.textHomeToggleAutomaticWater
        this.btnToggleAutomaticWater = binding.btnHomeToggleAutomaticWater
        this.btnWaterNow = binding.btnHomeWaterNow
    }

    private fun setViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    // Update UI elements
                    textIsAutomaticWater?.text  = automaticWaterText(it.isAutomaticWater)
                }
            }
        }
    }
}