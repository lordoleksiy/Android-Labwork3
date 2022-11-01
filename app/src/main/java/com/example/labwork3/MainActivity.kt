package com.example.labwork3

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.labwork3.fragments.Info
import com.example.labwork3.fragments.Input
import com.example.labwork3.fragments.Main
import com.example.labwork3.fragments.Result
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), Input.OnPasswordEnteredListener, Result.OnBackEvent {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragment(Main.newInstance())
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout, fragment)
            .commit()
    }

    override fun onPasswordEntered(pass: String) {
        setFragment(Result(pass))
    }

    override fun backEvent() {
        setFragment(Main.newInstance())
    }
}