package com.example.weatherapp.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.weatherapp.core.AbstractFragment
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.databinding.CityLayoutBinding
import com.example.weatherapp.main.MainActivity

class CityFragment : AbstractFragment<CityLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): CityLayoutBinding {
        return CityLayoutBinding.inflate(inflater, container, false)
    }

    private lateinit var viewModel: CityViewModel
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        viewModel = (activity as ProvideViewModel).viewModel(CityViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}