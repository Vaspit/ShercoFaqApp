package com.example.shercofaqapp.view.workshop.torques

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentTorquesBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.utils.CurrentBikeAddress
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel
import com.example.shercofaqapp.viewmodel.torques.RecyclerViewTorquesAdapter
import com.example.shercofaqapp.viewmodel.torques.TorquesViewModel
import kotlinx.coroutines.*


class TorquesFragment : Fragment() {

    private var currentBikeIndex = 0
    private var bikeId: Long = 0
    private lateinit var sharedPref: SharedPreferences
    private var currentBikeAddress = ""
    private lateinit var bikeModel: GarageFragmentViewModel
    private lateinit var torquesViewModel: TorquesViewModel
    lateinit var binding: FragmentTorquesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_torques, container, false)

        bikeModel = ViewModelProvider(this)[GarageFragmentViewModel::class.java]
        torquesViewModel = ViewModelProvider(this)[TorquesViewModel::class.java]

        sharedPref = binding.root.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        bikeId = sharedPref.getLong("bikeId", 0)
        Log.d("BIKE", "Torques fragment: ${bikeId.toString()}")

        bikeModel.bikes.observe(viewLifecycleOwner, Observer { bike ->
            //find updatable index of bike by bike id
            for (bikeItem: Int in bike.indices) {
                if (bike[bikeItem].bikeId == bikeId) {
                    currentBikeIndex = bikeItem
                    break
                }
            }

            currentBikeAddress = CurrentBikeAddress(bike, currentBikeIndex).getCurrentBikeAddress()

            CoroutineScope(Dispatchers.IO).launch {
                setRecyclerViews()
            }
        })

        return binding.root
    }

    private suspend  fun setRecyclerViews() {
        val engineTorques = torquesViewModel.getEngineTorques(requireContext(), currentBikeAddress)
        val chassisTorques = torquesViewModel.getChassisTorques(requireContext(), currentBikeAddress)

        withContext(Dispatchers.Main) {
            binding.engineRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.engineRecyclerView.adapter = RecyclerViewTorquesAdapter(engineTorques)
            binding.engineRecyclerView.setHasFixedSize(true)

            binding.chassisRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.chassisRecyclerView.adapter = RecyclerViewTorquesAdapter(chassisTorques)
            binding.chassisRecyclerView.setHasFixedSize(true)
        }
    }
}