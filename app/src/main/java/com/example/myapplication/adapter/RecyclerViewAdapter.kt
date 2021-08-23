package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ItemModel
import kotlinx.android.synthetic.main.item_layout.view.*

class RecyclerViewAdapter(private var itemList:MutableList<ItemModel>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    class  ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val itemName:TextView = itemView.item_name
        val itemSName:TextView = itemView.item_surname

        fun bind(item:ItemModel){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = itemList[position]
        holder.bind(listItem)

        holder.itemName.text = listItem.name
        holder.itemSName.text = listItem.surname
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItemList(list: MutableList<ItemModel>){
        itemList = list
        notifyDataSetChanged()
    }
}