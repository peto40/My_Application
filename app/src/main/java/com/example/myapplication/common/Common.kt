package com.example.myapplication.common

import com.example.myapplication.Client.RetrofitClient
import com.example.myapplication.Interface.RetrofitService

object Common {
    private val BASE_URL = "https://reqres.in/api/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}