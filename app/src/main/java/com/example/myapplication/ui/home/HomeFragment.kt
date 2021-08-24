package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Interface.RetrofitService
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.itemViewModel.ItemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var mService:RetrofitService
    private var _binding: FragmentHomeBinding? = null
    lateinit var viewModel:ItemViewModel
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var myItemAdapter: RecyclerViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        myItemAdapter = RecyclerViewAdapter(
            mutableListOf()
        )
        mService = Common.retrofitService
        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.itemRv.layoutManager = layoutManager
        binding.itemRv.adapter = myItemAdapter
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            viewModel.viewModelRequest()
            Log.d("coroutine","It's Ok")
        }
        viewModel.itemListLiveData.observe(this, Observer {
            myItemAdapter.setItemList(it)
        }
        )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}