package com.example.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.RecyclerViewAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.ItemModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    lateinit var layoutManager: LinearLayoutManager
    private lateinit var myItemAdapter: RecyclerViewAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
            mutableListOf(
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", ""),
                ItemModel("Exo", "yan", "", ""),
                ItemModel("Gagas", "67yan", "", "")
            )
        )
        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.itemRv.layoutManager = layoutManager
        binding.itemRv.adapter = myItemAdapter
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}