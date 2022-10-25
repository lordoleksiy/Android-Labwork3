package com.example.labwork3.fragments

import CustomPassword
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.labwork3.DataBaseHelper
import com.example.labwork3.R


class Input : Fragment() {
    private lateinit var editText: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText = view.findViewById(R.id.passwordInput)
        view.findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { group, id ->
            if (id == R.id.radioShow){
                editText.inputType = InputType.TYPE_CLASS_TEXT
                editText.transformationMethod = null
            }
            else{
                editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                editText.transformationMethod = CustomPassword()
            }
            editText.setSelection(editText.text.length)
        }
        view.findViewById<Button>(R.id.saveButton).setOnClickListener{
            writeDB()
        }
        view.findViewById<Button>(R.id.showButton).setOnClickListener{
            readDB()
        }
    }

    fun writeDB(){
        val text = editText.text.toString()
        when {
            text.isEmpty() -> {
                Toast.makeText(context, "Input any text!", Toast.LENGTH_SHORT).show()
            }
            text.all { it.isLetter() } -> {
                Toast.makeText(context, "Your password must contain both letters and numbers", Toast.LENGTH_SHORT).show()

            }
            text.all { it.isDigit() } -> {
                Toast.makeText(
                    context,
                    "Your password must contain both letters and numbers",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
                val dataBaseHelper = context?.let { DataBaseHelper(it) }
                dataBaseHelper?.addData(text)
                Toast.makeText(context, "Data is written to database!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun readDB(){
        val dataBaseHelper = context?.let { DataBaseHelper(it) }
        val data = dataBaseHelper?.getData()
        Log.i("tag", data.toString())
        Toast.makeText(context, data.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = Input()
    }
}