package com.example.shercofaqapp.view.workshop

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartsBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.SparePart
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.RecyclerViewSparePartsAdapter
import com.example.shercofaqapp.viewmodel.SparePartsFragmentViewModel

class SparePartsFragment : Fragment() {

    lateinit var binding: FragmentSparePartsBinding
    private lateinit var sharedPref: SharedPreferences
    private var bikeId: Long = 0
    private val bikeModel: GarageFragmentViewModel by viewModels()
    private val sparePartsModel: SparePartsFragmentViewModel by viewModels()
    lateinit var bike: Bike
    private var currentSparePartAddress = ""
    private lateinit var currentSparePartType: String
    private lateinit var currentSparePartName: String
    private var sparePartsArrayList = arrayListOf<SparePart>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_parts, container, false)

        binding.apply {
            var currentBikeIndex = 0
            val bikeObserver = Observer<List<Bike>> { bike ->
                //find updatable index of bike by bike id
                for (bikeItem: Int in bike.indices) {
                    if (bike[bikeItem].bikeId == bikeId) {
                        currentBikeIndex = bikeItem
//                        Log.d("SPARE_PARTS", currentBikeIndex.toString())
                        break
                    }
                }
                currentSparePartAddress =
                    bike[currentBikeIndex].bikeModelYear +
                            bike[currentBikeIndex].bikeType +
                            bike[currentBikeIndex].bikeEngineType +
                            bike[currentBikeIndex].bikeEngineVolume +
                            bike[currentBikeIndex].bikeEdition

                currentSparePartAddress.trim()

                val sparePartsArrayList = sparePartsModel.getSpareParts(
                    currentSparePartAddress,
                    currentSparePartType,
                    currentSparePartName,
                    requireContext()
                )

                if (sparePartsArrayList.size != 0) {
                    sparePartsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                    sparePartsRecyclerView.adapter = RecyclerViewSparePartsAdapter(sparePartsArrayList)
                    sparePartsRecyclerView.setHasFixedSize(true)
                }
            }

            //get bike id and current spare part
            sharedPref = root.context
                .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            bikeId = sharedPref.getLong("bikeId", 0)
            currentSparePartType = sharedPref.getString("currentSparePartType", "").toString()
            currentSparePartName = sharedPref.getString("currentSparePartName", "").toString()

            //forming parts address
            bikeModel.bikes.observe(viewLifecycleOwner, bikeObserver)
        }


        // Inflate the layout for this fragment
        return binding.root
    }

}