package com.example.weatherapp.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {
    fun show(supportFragmentManager : FragmentManager, containerId : Int)
    object Pop : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.popBackStack()
        }
    }
    abstract class Replace(
        private val fragmentClass : Class<out Fragment>
    ) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .replace(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .commit()
        }
    }
    abstract class Add (
        private val fragmentClass : Class<out Fragment>
    ) : Screen {
        override fun show(supportFragmentManager: FragmentManager, containerId: Int) {
            supportFragmentManager.beginTransaction()
                .add(containerId, fragmentClass.getDeclaredConstructor().newInstance())
                .hide(supportFragmentManager.fragments.last())
                .addToBackStack(fragmentClass.name)
                .commit()
        }
    }
}