package com.example.labwork3.fragments

import CustomPassword
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
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
    }

    companion object {
        @JvmStatic
        fun newInstance() = Input()
    }
}