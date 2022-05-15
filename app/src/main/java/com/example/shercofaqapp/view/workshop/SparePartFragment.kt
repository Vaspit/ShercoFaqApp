package com.example.shercofaqapp.view.workshop

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
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentSparePartBinding
import com.example.shercofaqapp.databinding.FragmentSparePartsBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import kotlin.properties.Delegates

class SparePartFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    lateinit var binding: FragmentSparePartBinding
    private val bikeModel: GarageFragmentViewModel by viewModels()
    private var bikeId: Long = 0
    lateinit var bike: Bike
    private var currentBikeIndex = 0
    private lateinit var currentSparePartDescription: String
    private lateinit var currentSparePartName: String
    private lateinit var currentSparePartLink: String
    private var currentSparePartImage = R.drawable.ic_baseline_parts

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_spare_part, container, false)

        binding.apply {
            val bikeObserver = Observer<List<Bike>> { bike ->
                //find updatable index of bike by bike id
                for (bikeItem: Int in bike.indices) {
                    if (bike[bikeItem].bikeId == bikeId) {
                        currentBikeIndex = bikeItem
                        break
                    }
                }
                sparePartImageView.setImageResource(currentSparePartImage)
                sparePartDescriptionTextView.text = currentSparePartDescription
            }

            //get bike id and current spare part
            sharedPref = root.context
                .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
            bikeId = sharedPref.getLong("bikeId", 0)
            currentSparePartName =
                sharedPref.getString("currentSparePartsName", "").toString()
            currentSparePartDescription =
                sharedPref.getString("currentSparePartDescription", "").toString()
            currentSparePartLink =
                sharedPref.getString("currentSparePartLink", "").toString()
            currentSparePartImage =
                sharedPref.getInt("currentSparePartImage", R.drawable.ic_baseline_parts)

            bikeModel.bikes.observe(viewLifecycleOwner, bikeObserver)
        }
        return binding.root
    }
}