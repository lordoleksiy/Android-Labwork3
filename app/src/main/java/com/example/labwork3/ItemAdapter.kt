package com.example.labwork3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ItemAdapter(val itemList: ArrayList<WriteModel>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rcv_items, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val curItem = itemList[position]
        val viewHolder = holder as ItemHolder
        viewHolder.pass.text = curItem.password
        viewHolder.time.text = curItem.time
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemHolder(item: View): RecyclerView.ViewHolder(item){
        val pass = item.findViewById<TextView>(R.id.textPass)
        val time = item.findViewById<TextView>(R.id.textTime)
    }
}