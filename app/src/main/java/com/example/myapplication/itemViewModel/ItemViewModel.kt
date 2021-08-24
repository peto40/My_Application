package com.example.myapplication.itemViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.common.Common
import com.example.myapplication.model.ItemModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemViewModel: ViewModel() {
    private val _itemListLiveData = MutableLiveData<MutableList<ItemModel>>()
    val itemListLiveData:LiveData<MutableList<ItemModel>>
    get() = _itemListLiveData

    fun viewModelRequest(){
        val mService = Common.retrofitService
        mService.getItemList().enqueue(object : Callback<MutableList<ItemModel>> {
            override fun onResponse(
                call: Call<MutableList<ItemModel>>,
                response: Response<MutableList<ItemModel>>
            ) {
                if (response.isSuccessful){
                    _itemListLiveData.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<MutableList<ItemModel>>, t: Throwable) {
                _itemListLiveData.postValue(mutableListOf())
            }
        })
    }

}