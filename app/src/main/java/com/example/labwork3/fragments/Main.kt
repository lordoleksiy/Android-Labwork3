package com.example.labwork3.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.labwork3.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class Main : Fragment() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        setFragment(Input.newInstance())
        bottomNavigationView = requireActivity().findViewById(R.id.bottomNav)

        bottomNavigationView.setOnItemSelectedListener {
            val id = it.itemId
            if (id == R.id.InfoItem)
                setFragment(Info.newInstance())
            else
                setFragment(Input.newInstance())
            return@setOnItemSelectedListener true
        }
    }

    private fun setFragment(fragment: Fragment){
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.placeholder, fragment)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Main()
    }
}