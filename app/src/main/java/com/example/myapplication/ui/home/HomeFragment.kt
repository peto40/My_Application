package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Interface.RetrofitService
import com.example.myapplication.adapter.ItemClickInterface
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.common.Common
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.itemViewModel.ItemViewModel
import com.example.myapplication.model.ItemModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment(), ItemClickInterface {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var mService: RetrofitService
    private var _binding: FragmentHomeBinding? = null
    private lateinit var viewModel: ItemViewModel
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var myItemAdapter: RecyclerViewAdapter
    private var itemListSize: Int? = null
    private lateinit var listener: ItemClickInterface

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        listener = this
        myItemAdapter = RecyclerViewAdapter(
            mutableListOf(), listener
        )
        mService = Common.retrofitService
        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.itemRv.layoutManager = layoutManager
        binding.itemRv.adapter = myItemAdapter
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)


        viewModel.itemListLiveData.observe(viewLifecycleOwner, {
            myItemAdapter.setItemList(it)
        })

        GlobalScope.launch(Dispatchers.IO) {
            viewModel.getItemListSizeRequest()
            withContext(Dispatchers.Main) {
                viewModel.itemListSizeData.observe(viewLifecycleOwner, {
                    itemListSize = it
                })
            }
            for (i in 1..itemListSize!!) {
                viewModel.getItemListRequest(i)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(item: ItemModel) {
        card_view.name.text = item.first_name
        card_view.surname.text = item.last_name
        card_view.item_email.text = item.email

        Picasso.get().load(item.avatar).into(card_view.item_image)
        card_view.isVisible = true
    }

}