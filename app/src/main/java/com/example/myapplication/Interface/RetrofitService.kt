package com.example.myapplication.Interface

import com.example.myapplication.model.ItemModel
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("messages1.json") //api/users
    fun getItemList(): Call<MutableList<ItemModel>>
}