package com.example.myapplication.Interface

import com.example.myapplication.model.ItemListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("users")
    fun getItemList(@Query("page") page: Int): Call<ItemListModel>

    @GET("users")
    fun getItemListSize(): Call<ItemListModel>
}