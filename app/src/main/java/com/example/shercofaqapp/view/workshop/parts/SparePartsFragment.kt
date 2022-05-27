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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartsBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.SparePart
import com.example.shercofaqapp.utils.CurrentBikeAddress
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.parts.RecyclerViewSparePartsAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SparePartsFragment : Fragment() {

    lateinit var binding: FragmentSparePartsBinding
    private var bikeId: Long = 0
    private val bikeModel: GarageFragmentViewModel by viewModels()
    private lateinit var sharedPref: SharedPreferences
    lateinit var bike: Bike
    lateinit var currentBikeAddress: String
    private lateinit var currentSparePartType: String
    private lateinit var currentSparePartName: String
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RecyclerViewSparePartsAdapter
    private lateinit var mRefSpareParts:DatabaseReference
    private var currentBikeIndex = 0
    private var bikes: List<Bike> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_parts, container, false)

        binding.apply {
            val bikeObserver = Observer<List<Bike>> { bike ->
                //find updatable index of bike by bike id
                for (bikeItem: Int in bike.indices) {
                    if (bike[bikeItem].bikeId == bikeId) {
                        currentBikeIndex = bikeItem
                        break
                    }
                }
                bikes = bike
                initRecyclerView(bikes, currentBikeIndex)
            }

            //get bike id and current spare part
            sharedPref = root.context
                .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            bikeId = sharedPref.getLong("bikeId", 0)
            currentSparePartType =
                sharedPref.getString("currentSparePartsType", "").toString()
            currentSparePartName =
                sharedPref.getString("currentSparePartsName", "").toString()

            bikeModel.bikes.observe(viewLifecycleOwner, bikeObserver)
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            initRecyclerView(bikes, currentBikeIndex)
            mAdapter.notifyDataSetChanged()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        return binding.root
    }

    private fun initRecyclerView(bike: List<Bike>, currentBikeIndex: Int) {
        currentBikeAddress = CurrentBikeAddress(bike, currentBikeIndex).getCurrentBikeAddress()
        mRecyclerView = binding.sparePartsRecyclerView
        mRefSpareParts = Firebase.database.getReference("parts")
            .child(currentBikeAddress)
            .child(currentSparePartType)
            .child(currentSparePartName)

        val options = FirebaseRecyclerOptions.Builder<SparePart>()
            .setQuery(mRefSpareParts, SparePart::class.java)
            .build()

        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = RecyclerViewSparePartsAdapter(options)
        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }
}