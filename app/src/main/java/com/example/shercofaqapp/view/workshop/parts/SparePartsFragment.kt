package com.example.shercofaqapp.view.workshop.parts

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartsBinding
import com.example.shercofaqapp.viewmodel.parts.RecyclerViewSparePartsAdapter
import com.example.shercofaqapp.viewmodel.parts.SparePartsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SparePartsFragment : Fragment() {

    lateinit var binding: FragmentSparePartsBinding
    private lateinit var currentBikeAddress: String
    private lateinit var currentSparePartType: String
    private lateinit var currentSparePartName: String
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RecyclerViewSparePartsAdapter
    private val sparePartsViewModel: SparePartsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_parts, container, false)

        /** Get bike address, current spare parts type and spare parts name */
        getOuterArguments(binding.root)

        setRecyclerView(
            sparePartsViewModel,
            currentBikeAddress,
            currentSparePartType,
            currentSparePartName
        )

        binding.swipeRefreshLayout.setOnRefreshListener { onRefresh() }

        return binding.root
    }

    private fun setRecyclerView(
        viewModel: SparePartsViewModel,
        currentBikeAddress: String,
        currentSparePartType: String,
        currentSparePartName: String
    ){
        mRecyclerView = binding.sparePartsRecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = viewModel.getRecyclerViewAdapter(
            currentBikeAddress,
            currentSparePartType,
            currentSparePartName
        )
        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    private fun getOuterArguments(view: View) {
        val sharedPref = view.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        currentBikeAddress = sharedPref.getString("currentBikeAddress", "").toString()
        currentSparePartType = arguments?.getString("currentSparePartsType").toString()
        currentSparePartName = arguments?.getString("currentSparePartsName").toString()
    }

    override fun onPause() {
        super.onPause()
        mAdapter.stopListening()
    }

    private fun onRefresh() {
        setRecyclerView(
            sparePartsViewModel,
            currentBikeAddress,
            currentSparePartType,
            currentSparePartName)
        binding.swipeRefreshLayout.isRefreshing = false
    }
}