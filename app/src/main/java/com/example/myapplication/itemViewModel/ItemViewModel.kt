package com.example.myapplication.itemViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.common.Common
import com.example.myapplication.model.ItemModel

class ItemViewModel : ViewModel() {
    private val _itemListLiveData = MutableLiveData<MutableList<ItemModel>>()
    val itemListLiveData: LiveData<MutableList<ItemModel>>
        get() = _itemListLiveData

    private val _itemListSizeData = MutableLiveData<Int>()
    val itemListSizeData: LiveData<Int>
        get() = _itemListSizeData

//    fun viewModelRequest(){
//        val mService = Common.retrofitService
//        mService.getItemList().enqueue(object : Callback<MutableList<ItemModel>> {
//            override fun onResponse(
//                call: Call<MutableList<ItemModel>>,
//                response: Response<MutableList<ItemModel>>
//            ) {
//                if (response.isSuccessful){
//                    _itemListLiveData.postValue(response.body())
//                    Log.d("response", "asfas")
//
//                }
//            }
//            override fun onFailure(call: Call<MutableList<ItemModel>>, t: Throwable) {
//                _itemListLiveData.postValue(mutableListOf())
//                Log.d("response", t.stackTraceToString())
//            }
//        })
//    }

    fun getItemListSizeRequest() {
        val response = Common.retrofitService.getItemListSize().execute()
        _itemListSizeData.postValue(response.body()?.total_pages)
    }

    fun getItemListRequest(page: Int) {
        val response = Common.retrofitService.getItemList(page).execute()
        _itemListLiveData.postValue(response.body()?.data)
    }

}