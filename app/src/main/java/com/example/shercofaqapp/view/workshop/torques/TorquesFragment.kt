package com.example.shercofaqapp.view.workshop.torques

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
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentTorquesBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.model.Torque
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
    private val bikeModel: GarageFragmentViewModel by viewModels()
    lateinit var binding: FragmentTorquesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

            CoroutineScope(Dispatchers.IO).launch {
                setRecyclerViews()
            }
        }

        sharedPref = binding.root.context
            .getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        bikeId = sharedPref.getLong("bikeId", 0)

        bikeModel.bikes.observe(viewLifecycleOwner, bikeObserver)

        return binding.root
    }

    private suspend  fun setRecyclerViews() {
        val engineTorques = getEngineTorquesFromViewModel()
        val chassisTorques = getChassisTorquesFromViewModel()

        withContext(Dispatchers.Main) {
            binding.engineRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.engineRecyclerView.adapter = RecyclerViewTorquesAdapter(engineTorques)
            binding.engineRecyclerView.setHasFixedSize(true)

            binding.chassisRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.chassisRecyclerView.adapter = RecyclerViewTorquesAdapter(chassisTorques)
            binding.chassisRecyclerView.setHasFixedSize(true)
        }
    }

    private fun getEngineTorquesFromViewModel(): ArrayList<Torque> {
        return TorquesViewModel(requireContext()).getEngineTorques(currentBikeAddress)
    }

    private fun getChassisTorquesFromViewModel(): ArrayList<Torque> {
        return TorquesViewModel(requireContext()).getChassisTorques(currentBikeAddress)
    }
}