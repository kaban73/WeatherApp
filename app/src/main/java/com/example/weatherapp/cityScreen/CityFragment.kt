package com.example.weatherapp.cityScreen

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.core.AbstractFragment
import com.example.weatherapp.core.ProvideViewModel
import com.example.weatherapp.databinding.CityFragmentBinding

class CityFragment : AbstractFragment<CityFragmentBinding>() {
    companion object {
        fun newInstance(cityName : String) : CityFragment {
            val instance = CityFragment()
            instance.arguments = Bundle().apply {
                putString(KEY, cityName)
            }
            return instance
        }
        private const val KEY = "cityNameKey"
    }
    override fun bind(inflater: LayoutInflater, container: ViewGroup?): CityFragmentBinding {
        return CityFragmentBinding.inflate(inflater, container, false)
    }

    lateinit var viewModel : CityViewModel
    lateinit var recyclerView: RecyclerView
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            viewModel.comeback()
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = b.cityRecyclerView
        viewModel = (activity as ProvideViewModel).viewModel(CityViewModel::class.java)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
        val cityName = requireArguments().getString(KEY)
        b.cityEditText.setText(cityName)

        b.cityEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                viewModel.findCities(b.cityEditText.text.toString())
                return@setOnEditorActionListener true
            }
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.remove()
    }
}