package com.example.shercofaqapp.view.workshop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartsBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.SparePart
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.SparePartsAdapter
import com.firebase.ui.database.FirebaseRecyclerAdapter
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
    private var currentSparePartAddress = ""
    private lateinit var currentSparePartType: String
    private lateinit var currentSparePartName: String
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: SparePartsAdapter
    private lateinit var mRefSpareParts:DatabaseReference
    private var currentBikeIndex = 0

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
                initRecyclerView1(bike, currentBikeIndex)
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

        return binding.root
    }

    private fun initRecyclerView1(bike: List<Bike>, currentBikeIndex: Int) {
        mRecyclerView = binding.sparePartsRecyclerView
        mRefSpareParts = Firebase.database.getReference("parts")
            .child(getCurrentSparePartAddress(bike, currentBikeIndex))
            .child(currentSparePartType)
            .child(currentSparePartName)

        Log.d("RecyclerViewDebugging", "" + mRefSpareParts)

        val options = FirebaseRecyclerOptions.Builder<SparePart>()
            .setQuery(mRefSpareParts, SparePart::class.java)
            .build()

        mRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mAdapter = SparePartsAdapter(options)
        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    private fun getCurrentSparePartAddress(
        bike: List<Bike>,
        currentBikeIndex: Int
    ): String {
        currentSparePartAddress =
            bike[currentBikeIndex].bikeModelYear +
                    bike[currentBikeIndex].bikeType +
                    bike[currentBikeIndex].bikeEngineType +
                    bike[currentBikeIndex].bikeEngineVolume +
                    bike[currentBikeIndex].bikeEdition

       return currentSparePartAddress.trim()
    }
}