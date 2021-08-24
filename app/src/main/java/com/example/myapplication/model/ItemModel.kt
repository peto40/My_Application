package com.example.myapplication.model

data class ItemModel(
    val first_name:String, //first_name
    val last_name:String,
    val email:String,
    val avatar:String?
)

data class ItemListModel(
    val page:Int,
    val total_pages:Int,
    val data:MutableList<ItemModel>
)