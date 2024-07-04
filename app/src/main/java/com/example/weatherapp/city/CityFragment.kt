package com.example.weatherapp.city

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import com.example.weatherapp.city.core.CityData
import com.example.weatherapp.city.core.CityViewModel
import com.example.weatherapp.city.list.ChooseCity
import com.example.weatherapp.city.list.CityAdapter
import com.example.weatherapp.core.AbstractFragment
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.databinding.CityLayoutBinding

class CityFragment : AbstractFragment<CityLayoutBinding>() {
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): CityLayoutBinding {
        return CityLayoutBinding.inflate(inflater, container, false)
    }

    private lateinit var viewModel: CityViewModel
    private val cityAdapter = CityAdapter(chooseCity = object : ChooseCity {
        override fun choose(cityData: CityData) {
            viewModel.chooseCity(cityData)
            b.cityEditText.setText(cityData.name)
        }
    })
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        viewModel = (activity as ProvideViewModel).viewModel(CityViewModel::class.java)
        b.cityRecyclerView.adapter = cityAdapter
        viewModel.cityListLiveData().observe(viewLifecycleOwner) {
            cityAdapter.update(it)
        }
        b.cityEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.findCity(b.cityEditText.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
        viewModel.init(b.cityEditText)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}