package com.example.clientapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class CHAdapter(private val clientlog:ArrayList<User>) : RecyclerView.Adapter<CHAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.client_item,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = clientlog[position]
        holder.col1.text = currentitem.empid
        holder.col2.text = currentitem.LogStat
    }

    override fun getItemCount(): Int {
        return clientlog.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val col1 : TextView = itemView.findViewById(R.id.LogID)
        val col2 : TextView = itemView.findViewById(R.id.LogStat)
    }
}
