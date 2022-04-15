package com.example.shercofaqapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shercofaqapp.R
import com.example.shercofaqapp.databinding.FragmentGarageBinding
import com.example.shercofaqapp.model.Bike
import com.example.shercofaqapp.viewmodel.BikeAdapter
import com.example.shercofaqapp.viewmodel.GarageFragmentViewModel


class GarageFragment : Fragment() {

    lateinit var binding: FragmentGarageBinding
    private val adapter = BikeAdapter()
    private val model: GarageFragmentViewModel by viewModels()
    private var bikeArrayList: ArrayList<Bike> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_garage, container, false)
        initialization()


        return binding.root

    }

    private fun initialization() {

        binding.apply {

            loadBikesInArray()

            garageRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            garageRecyclerView.adapter = adapter

//            model.bikes.observe(viewLifecycleOwner, Observer {
//
//                bikeArrayList = model.bikes as ArrayList<Bike>
//
//            })

//            adapter.addBikeList(bikeArrayList)

            floatingActionButton.setOnClickListener {



            }

        }

    }

    private fun loadBikesInArray() {

        val bike1 = Bike()
        bike1.bikeName = "Sherco"
        bike1.bikeModelYear = "2019"
        bike1.bikeEngineType = "2S"
        bike1.bikeEngineVolume = "300cc"
        bike1.bikeEdition = "Factory"
        bike1.bikeImage = R.drawable.garage_item_icon

        val bike2 = Bike()
        bike2.bikeName = "KTM TPI"
        bike2.bikeModelYear = "2017"
        bike2.bikeEngineType = "2S"
        bike2.bikeEngineVolume = "250cc"
        bike2.bikeEdition = "Regular"
        bike2.bikeImage = R.drawable.garage_item_icon

        val bike3 = Bike()
        bike3.bikeName = "KTM"
        bike3.bikeModelYear = "2013"
        bike3.bikeEngineType = "4S"
        bike3.bikeEngineVolume = "350cc"
        bike3.bikeEdition = "Six Days"
        bike3.bikeImage = R.drawable.garage_item_icon


        model.addNewBike(bike1)
        model.addNewBike(bike2)
        model.addNewBike(bike3)

    }

}