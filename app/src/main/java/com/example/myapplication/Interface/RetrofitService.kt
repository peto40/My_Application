package com.example.myapplication.Interface

import com.example.myapplication.model.ItemModel
import retrofit2.Call
import retrofit2.http.GET

abstract class RetrofitService {
    @GET("users?page=1")
    abstract fun getItemList(): Call<MutableList<ItemModel>>
}