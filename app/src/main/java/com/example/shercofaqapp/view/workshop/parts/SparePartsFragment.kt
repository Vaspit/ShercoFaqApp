package com.example.shercofaqapp.view.workshop.parts

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartsBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.parts.RecyclerViewSparePartsAdapter
import com.example.shercofaqapp.viewmodel.parts.SparePartsViewModel

class SparePartsFragment : Fragment() {

    lateinit var binding: FragmentSparePartsBinding
    private var bikeId: Long = 0
    private val bikeModel: GarageFragmentViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences
    lateinit var bike: Bike
    private lateinit var currentSparePartType: String
    private lateinit var currentSparePartName: String
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RecyclerViewSparePartsAdapter
    private lateinit var sparePartsViewModel: SparePartsViewModel
    private var currentBikeIndex = 0
    private var bikes: List<Bike> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_parts, container, false)
        sparePartsViewModel = ViewModelProvider(this)[SparePartsViewModel::class.java]

        /** Get bike id and current spare part */
        getOuterArguments(binding.root)

        bikeModel.bikes.observe(viewLifecycleOwner, Observer<List<Bike>> { bike ->
            //find updatable index of bike by bike id
            for (bikeItem: Int in bike.indices) {
                if (bike[bikeItem].bikeId == bikeId) {
                    currentBikeIndex = bikeItem
                    break
                }
            }
            bikes = bike
            setRecyclerView(bikes, currentBikeIndex, sparePartsViewModel, currentSparePartType,
                currentSparePartName)
        })

        binding.swipeRefreshLayout.setOnRefreshListener { onRefresh() }

        return binding.root
    }

    private fun setRecyclerView(bike: List<Bike>,
                                currentBikeIndex: Int,
                                viewModel: SparePartsViewModel,
                                currentSparePartType: String,
                                currentSparePartName: String
    ){
        mRecyclerView = binding.sparePartsRecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = viewModel.getRecyclerViewAdapter(
            bike,
            currentBikeIndex,
            currentSparePartType,
            currentSparePartName
        )
        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    private fun getOuterArguments(view: View) {
        sharedPref = view.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        bikeId = sharedPref.getLong("bikeId", 0)

        currentSparePartType = arguments?.getString("currentSparePartsType").toString()
        currentSparePartName = arguments?.getString("currentSparePartsName").toString()
    }

    override fun onPause() {
        super.onPause()
        mAdapter.stopListening()
    }

    private fun onRefresh() {
        setRecyclerView(bikes, currentBikeIndex, sparePartsViewModel, currentSparePartType,
            currentSparePartName)
        binding.swipeRefreshLayout.isRefreshing = false
    }
}