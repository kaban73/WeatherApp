package com.example.weatherapp.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class AbstractFragment<B : ViewBinding> : Fragment() {
    protected var _binding: B? = null
    protected val b get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =  bind(inflater, container)
        return b.root
    }
    protected abstract fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) : B
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}