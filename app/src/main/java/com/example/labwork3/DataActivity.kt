package com.example.labwork3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DataActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
    }

    override fun onStart() {
        super.onStart()
        val dataBaseHelper = DataBaseHelper(this)
        val data = dataBaseHelper.getData()
        if (data.isEmpty()){
            Toast.makeText(this, "You didn't enter any data!", Toast.LENGTH_LONG).show()
            return
        }
        val itemAdapter = ItemAdapter(ArrayList(data))
        val chatRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = itemAdapter
    }
}