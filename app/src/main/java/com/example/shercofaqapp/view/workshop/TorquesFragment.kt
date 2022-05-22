package com.example.shercofaqapp.view.workshop

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentFaqBinding
import com.example.shercofaqapp.databinding.FragmentTorquesBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.TorqueDatabase
import com.example.shercofaqapp.utils.CurrentBikeAddress
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import java.io.IOException
import java.nio.charset.Charset


class TorquesFragment : Fragment() {

    private var currentBikeIndex = 0
    private var bikeId: Long = 0
    private lateinit var sharedPref: SharedPreferences
    var currentBikeAddress = ""
    private lateinit var torqueDatabase: String
    private val bikeModel: GarageFragmentViewModel by viewModels()

    lateinit var binding: FragmentTorquesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_torques, container, false)

        val bikeObserver = Observer<List<Bike>> { bike ->
            //find updatable index of bike by bike id
            for (bikeItem: Int in bike.indices) {
                if (bike[bikeItem].bikeId == bikeId) {
                    currentBikeIndex = bikeItem
                    break
                }
            }
            currentBikeAddress = CurrentBikeAddress(bike, currentBikeIndex).getCurrentBikeAddress()
            Log.d("FRAGMENT_TORQUE", currentBikeAddress)

            torqueDatabase = TorqueDatabase(requireContext()).getTorque(currentBikeAddress).toString()
//            Log.d("FRAGMENT_TORQUE", torqueDatabase)
        }

        sharedPref = binding.root.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        bikeId = sharedPref.getLong("bikeId", 0)

        bikeModel.bikes.observe(viewLifecycleOwner, bikeObserver)

        return binding.root

    }

}